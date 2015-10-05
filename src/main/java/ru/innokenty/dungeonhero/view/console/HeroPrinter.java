package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Experience;
import ru.innokenty.dungeonhero.model.Hero;
import ru.innokenty.dungeonhero.model.Skill;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class HeroPrinter implements Printer<Hero> {

    private static final HeroPrinter instance = new HeroPrinter();

    public static HeroPrinter getInstance() {
        return instance;
    }

    private HeroPrinter() {
    }

    public String stringify(Hero hero) {
        StringBuilder builder = new StringBuilder();
        builder.append("====SKILLS====\n");
        Skill.forEach(skill -> builder.append(skill.toString())
                                      .append(" => ")
                                      .append(hero.get(skill))
                                      .append("\n"));
        Experience exp = hero.getExperience();
        builder.append("====LEVEL====\n")
               .append(exp.getLevel()).append("\n")
               .append("====EXPERIENCE====\n")
               .append("total => ").append(exp.getExpTotal()).append("\n")
               .append("next level => ")
               .append(exp.getExpSinceLastLevel()).append("/").append(exp.getExpForNextLevel()).append("\n");
        return builder.toString();
    }
}
