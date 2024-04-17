package id.ac.ui.cs.advprog.auth.service.command;

public interface Command<I, O> {
    O execute(I payload);
}
