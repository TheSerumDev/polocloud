package dev.httpmarco.polocloud.node.terminal.setup.common;

import dev.httpmarco.polocloud.node.Node;
import dev.httpmarco.polocloud.node.terminal.setup.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class AbstractQuestion implements Question {

    private final String question;

    public void print(String setupMessage) {
        Node.instance().terminal().printLine(setupMessage);
    }

}