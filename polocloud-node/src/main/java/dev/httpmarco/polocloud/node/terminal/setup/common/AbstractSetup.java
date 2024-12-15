package dev.httpmarco.polocloud.node.terminal.setup.common;

import dev.httpmarco.polocloud.node.Node;
import dev.httpmarco.polocloud.node.terminal.setup.Question;
import dev.httpmarco.polocloud.node.terminal.setup.Setup;
import dev.httpmarco.polocloud.node.terminal.setup.SetupStep;
import dev.httpmarco.polocloud.node.terminal.setup.impl.SetupStepImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.LinkedList;
import java.util.List;

@Getter
@Log4j2
@Accessors(fluent = true)
public abstract class AbstractSetup implements Setup {

    private final String id;
    private final List<SetupStep> steps = new LinkedList<>();

    private int index = 0;

    public AbstractSetup(String id) {
        this.id = id;
        this.bindQuestion();
    }

    public abstract void bindQuestion();

    @Override
    public void next() {
        index++;
        this.redisplay();
        //todo check last
    }

    @Override
    public void previous() {
        if(index != 0) {
            index--;
        }
        this.redisplay();
    }

    @Override
    public SetupStep current() {
        return this.steps.get(index);
    }

    public void add(Question question) {
        this.steps.add(new SetupStepImpl(question));
    }

    @Override
    public void start() {
        this.redisplay();
    }

    @Override
    public void close() {

    }

    @Override
    public void redisplay() {
        var terminal = Node.instance().terminal();

        terminal.clear();
        terminal.printLine(" ");
        terminal.printLine("Question: " + this.current().question().question());
        terminal.printLine(" ");
        this.current().question().display();
        terminal.printLine(" ");
        terminal.printLine(" ");
        terminal.printLine("&eTip! &7Use the arrow keys to navigate through the options&8.");
        terminal.printLine(" ");
    }
}