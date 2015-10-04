package ru.innokenty.dungeonhero;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class DungeonHero {

    private final CommandIterator commandIterator;

    public DungeonHero(CommandIterator commandIterator) {
        this.commandIterator = commandIterator;
    }

    public void start() {
        commandIterator.forEachRemaining(this::handle, this::handleException);
    }

    private void handle(Command command) {
        System.out.println(command);
    }

    private void handleException(UnsupportedCommandException e) {
        System.err.println("Command '" + e.getCommand() + "' not supported!");
    }
}
