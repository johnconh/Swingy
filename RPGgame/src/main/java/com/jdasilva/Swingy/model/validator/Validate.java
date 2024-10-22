package com.jdasilva.Swingy.model.validator;

import com.jdasilva.Swingy.model.hero.Hero;
import com.jdasilva.Swingy.model.hero.Artifact;
import com.jdasilva.Swingy.model.enemy.Enemy;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;


public class Validate {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();

    public static boolean validateHero(Hero hero){
        Set<ConstraintViolation<Hero>> violations = validator.validate(hero);
        if(violations.size() > 0){
            for(ConstraintViolation<Hero> violation : violations){
                System.out.println(violation.getMessage());
            }
            return false;
        }
        return true;
    }

    public static boolean validateEnemy(Enemy enemy){
        Set<ConstraintViolation<Enemy>> violations = validator.validate(enemy);
        if(violations.size() > 0){
            for(ConstraintViolation<Enemy> violation : violations){
                System.out.println(violation.getMessage());
            }
            return false;
        }
        return true;
    }

    public static boolean validateArtifact(Artifact artifact){
        Set<ConstraintViolation<Artifact>> violations = validator.validate(artifact);
        if(violations.size() > 0){
            for(ConstraintViolation<Artifact> violation : violations){
                System.out.println(violation.getMessage());
            }
            return false;
        }
        return true;
    }
}
