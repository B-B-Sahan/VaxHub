package lk.ijse.vaxhub.util;

import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;


    public class ValidationUtil {
        public static Object validation(LinkedHashMap<TextField, Pattern> map2) {
            for (TextField key : map2.keySet()) {
                Pattern pattern = map2.get(key);
                if (pattern.matcher(key.getText()).matches()) {
                    removeError(key);
                } else {
                    addError(key);
                }
            }
            return null;
        }

        private static void addError(TextField key) {
       key.setStyle("-fx-border-color: red; -fx-background-radius: 5; -fx-background-radius: 5");

        }

        private static void removeError(TextField key) {
            key.setStyle("-fx-border-color: green; -fx-background-radius: 5; -fx-background-radius: 5");

        }


    }

