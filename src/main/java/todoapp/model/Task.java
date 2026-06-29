package todoapp.model;

public class Task {
    private int id;

    private String title;

    private boolean done;



    public Task(int id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
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



    @Override
    public String toString() {
        String status = done ? "✔ ":"✖";
       return String.format("%d | %s | %s", id, title,status);




    }
}
