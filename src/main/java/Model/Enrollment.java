package Model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Jeroen Roovers
 */
public class Enrollment {

    private static final String illegalStatusMessage = "Status while instantiating new Enrollment class was not 0, 1 or 2";

    private long id;
    private Lesson lesson;
    private User student;
    private Calendar signupTime;

    private User manager;
    private Calendar approvalTime;
    private boolean accepted;
    private boolean rejected;
    private String comment;

    /**
     * Creates a new enrollment object with status 'awaiting approval'
     *
     * @param l lesson student enrolled to
     * @param s student that enrolled to lesson
     */
    public Enrollment(Lesson l, User s) {
        this.lesson = l;
        this.student = s;
        this.signupTime = new GregorianCalendar();
        this.accepted = false;
        this.rejected = false;
    }

    /**
     * Database representation of enrollment. Constructor takes all non-null
     * fields.
     *
     * @param id
     * @param lesson_id
     * @param student_id
     * @param signupTime
     * @param status
     */
    public Enrollment(long id, long lesson_id, long student_id, Calendar signupTime, int status) {
        this.id = id;
        Lesson l = new Lesson();
        l.setId(lesson_id);
        this.lesson = l;
        User s = new User();
        s.setId(student_id);
        this.student = s;

        switch (status) {
            case 0:
                this.accepted = false;
                this.rejected = false;
                break;
            case 1:
                this.accepted = true;
                this.rejected = false;
                break;
            case 2:
                this.accepted = false;
                this.rejected = true;
                break;
            default:
                throw new IllegalArgumentException(illegalStatusMessage);
        }
    }

    /**
     * Approves this enrollment
     *
     * @param manager manager that accepts this enrollment
     */
    public void Approve(User manager) {
        this.manager = manager;
        this.approvalTime = new GregorianCalendar();
        this.accepted = true;
        this.rejected = false;
    }

    /**
     * Rejects this enrollment
     *
     * @param manager manager that rejects this enrollment
     */
    public void Reject(User manager) {
        this.manager = manager;
        this.approvalTime = new GregorianCalendar();
        this.accepted = false;
        this.rejected = true;
    }

    public String getStatus() {
        if (accepted && rejected) {
            return "Fout: akkoord EN afgewezen";
        } else if (accepted) {
            return "akkoord";
        } else if (rejected) {
            return "afgewezen";
        } else {
            return "in afwachting";
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Calendar getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Calendar signupTime) {
        this.signupTime = signupTime;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Calendar getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Calendar approvalTime) {
        this.approvalTime = approvalTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.lesson);
        hash = 37 * hash + Objects.hashCode(this.student);
        hash = 37 * hash + Objects.hashCode(this.signupTime);
        hash = 37 * hash + Objects.hashCode(this.manager);
        hash = 37 * hash + Objects.hashCode(this.approvalTime);
        hash = 37 * hash + (this.accepted ? 1 : 0);
        hash = 37 * hash + (this.rejected ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.comment);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enrollment other = (Enrollment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.accepted != other.accepted) {
            return false;
        }
        if (this.rejected != other.rejected) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.lesson, other.lesson)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.signupTime, other.signupTime)) {
            return false;
        }
        if (!Objects.equals(this.manager, other.manager)) {
            return false;
        }
        if (!Objects.equals(this.approvalTime, other.approvalTime)) {
            return false;
        }
        return true;
    }

}
