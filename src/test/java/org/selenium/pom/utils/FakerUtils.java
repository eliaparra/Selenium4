package org.selenium.pom.utils;

import com.github.javafaker.Faker;

//esta librería y clase generan datos fake con los que testar. en este caso, lo utilizaré para generar un número aleatorio (que luego se concatenará
// al nombre de usuario)
public class FakerUtils {
    public Long generateRandomNumber() {
        Faker faker = new Faker();
        return faker.number().randomNumber(10, true);
    }
}
