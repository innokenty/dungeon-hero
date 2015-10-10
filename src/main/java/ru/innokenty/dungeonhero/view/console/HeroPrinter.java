package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Experience;
import ru.innokenty.dungeonhero.model.Hero;
import ru.innokenty.dungeonhero.model.Skill;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class HeroPrinter implements Printer<Hero> {

    @Override
    public boolean accept(Object printable) {
        return printable instanceof Hero;
    }

    public String print(Hero hero) {
        Experience exp = hero.getExperience();

        StringBuilder builder = new StringBuilder();

        builder.append("===== LEVEL ================\n")
               .append(exp.getLevel()).append("\n")
               .append("===== SKILLS ===============\n");
        Skill.forEach(skill -> builder
               .append(skill.toString())
               .append(" => ")
               .append(hero.get(skill))
               .append("\n"));
        builder.append("===== EXPERIENCE ===========\n")
               .append("total => ").append(exp.getExpTotal()).append("\n")
               .append("next level => ")
               .append(exp.getExpSinceLastLevel()).append("/").append(exp.getExpForNextLevel()).append("\n");
        builder.append("===== CALCULATED STATS =====\n")
               .append("HP => ").append(hero.getHealthTotal()).append("\n")
               .append("damage => ").append(hero.getMinDamage()).append("-").append(hero.getMaxDamage()).append("\n");
        return builder.toString();
    }
}
