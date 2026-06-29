package todoapp.model;

import java.time.LocalDate;

public class Task {
    private int id;

    private String title;

    private boolean done;

    private LocalDate createdate;



    public Task(int id, String title, boolean done, LocalDate createdate) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.createdate=createdate;
    }



    public Task(String title) {
        this.title = title;
    }



    public int getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }



    public boolean isDone() {
        return done;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public void setDone(boolean done) {
        this.done = done;
    }



    public LocalDate getCreatedate() {
        return createdate;
    }



    public void setCreatedate(LocalDate createdate) {
        this.createdate = createdate;
    }



    @Override
    public String toString() {
        String status = done ? "DONE ":"TODO";
       return String.format("| %s | %s | %s ", createdate, title,status );   // id убрали, потому что номер задачи считаем в цикле вместо получения из бд




    }
}
