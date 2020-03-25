package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.Meal;
import com.epam.web.exception.ServiceException;
import com.epam.web.reader.ConfigReader;
import com.epam.web.service.MealService;
import com.epam.web.validation.NewMealValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.epam.web.command.constants.CommandConst.MENU;

public class NewMealCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(NewMealCommand.class);

    private MealService service;
    private NewMealValidator validator;

    public NewMealCommand(MealService service, NewMealValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String name = request.getParameter(Meal.NAME);
        String description = request.getParameter(Meal.DESCRIPTION);
        String tempPrice = request.getParameter(Meal.PRICE);

        String image;

        try {
            Part filePart = request.getPart("image");
            image = getFileName(filePart);

            checkMealParameters(name, description, tempPrice, image);

            String filePath = ConfigReader.read("path.save");
            String path = filePath + image;
            byte[] buffer = getImageBytes(filePart);
            saveImage(buffer, path);

        } catch (IOException | ServletException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("Failed to load image");
        }

        Meal.Builder builder = Meal.newBuilder();

        builder.buildImage(image);
        builder.buildName(name);
        builder.buildDescription(description);

        BigDecimal price = new BigDecimal(tempPrice);
        builder.buildPrice(price);

        Meal meal = builder.build();

        service.saveMeal(meal);
        return CommandResult.redirect("controller?command=" + MENU);
    }

    private byte[] getImageBytes(Part part) throws IOException {
        try(InputStream inputStream = part.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }

    private String getFileName(Part part) {
        String submittedFileName = part.getSubmittedFileName();
        Path path = Paths.get(submittedFileName).getFileName();
        return path.toString();
    }

    private void saveImage(byte[] buffer, String path) throws IOException {
        try(FileOutputStream outputStream = new FileOutputStream(new File(path))) {
            outputStream.write(buffer);
        }
    }

    private void checkMealParameters(String name, String description, String price, String image) {
        if (!validator.verifyName(name)
                || !validator.verifyDescription(description)
                || !validator.verifyPrice(price)
                || !validator.verifyImage(image)) {
            throw new IllegalArgumentException("Wrong parameters");
        }
    }
}
