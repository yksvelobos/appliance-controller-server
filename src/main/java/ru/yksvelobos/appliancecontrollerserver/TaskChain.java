package ru.yksvelobos.appliancecontrollerserver;

import ru.yksvelobos.appliancecontrollerserver.entity.Action;
import ru.yksvelobos.appliancecontrollerserver.entity.WorkProgram;

/**
 * Chain-of-responsibility
 *
 * @author Ilia Sobolevsky
 */
public class TaskChain implements Task, Runnable {
    private Task next;
    private Action action;
    private Strategy<Action> strategy;

    protected TaskChain(Action action, Strategy<Action> strategy) {
       this.strategy = strategy;
       this.action = action;
    }

    @Override
    public Task setNext(Task task) {
        this.next = task;
        return task;
    }
    @Override
    public void execute() {
        strategy.execute(action);

        if (next != null) {
            next.execute();
        }
    }

    @Override
    public void run() {
        execute();
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private WorkProgram workProgram;
        private Strategy<Action> strategy;

        public  TaskBuilder fromProgram(WorkProgram workProgram, Strategy<Action> strategy) {
            this.strategy = strategy;
            this.workProgram = workProgram;

            return this;
        }
        public Task buildTask() {
            Task resultTask = null;
            for(Action action : workProgram.getActions()) {
                if (resultTask == null) {
                    resultTask = new TaskChain(action, strategy);
                }
                resultTask.setNext(new TaskChain(action, strategy));
            }
            return resultTask;
        }
    }
}
