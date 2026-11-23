public class Set {
    // private members
    private String hobby;
    private int classes;      // classes per month
    private String category;
    private int pleasure;     // 1..10
    // public member
    public static final int MAX_PLEASURE = 10;
    public Set(String hobby, int classes, String category, int pleasure) {
        this.hobby = hobby;
        this.category = category;
        setClasses(classes);
        setPleasure(pleasure);
    }
    // getters and setters
    public String getHobby() { return hobby; }
    public void setHobby(String hobby) { this.hobby = hobby; }
    public void setTitle(String name2) { this.hobby = name2; }
    public int getClasses() { return classes; }
    public void setClasses(int classes) {
        if (classes < 0) classes = 0;
        this.classes = classes;
    }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getPleasure() { return pleasure; }
    public void setPleasure(int pleasure) {
        if (pleasure < 1) pleasure = 1;
        if (pleasure > MAX_PLEASURE) pleasure = MAX_PLEASURE;
        this.pleasure = pleasure;
    }
    // short one-line description
    public String getTitle() {
        return hobby + " (" + category + "), classes: " + classes
                + ", pleasure: " + pleasure + "/" + MAX_PLEASURE;
    }
    // detailed description
    public String getDescription() {
            return "Hobby: " + hobby + "\n"
                    + "Category: " + category + "\n"
                    + "Amount classes: " + classes + "\n"
                    + "Great pleasure: " + pleasure + "/" + MAX_PLEASURE + "\n"
                    + "Level: " + getLevel() + "\n"
                    + "Pleasure per class: " + String.format("%.2f", getPleasurePerClass());
    }
    // additional methods
    public double getPleasurePerClass() {
        if (classes == 0) return 0;
        return (double) pleasure / classes;
    }
    public String getLevel() {
        if (pleasure >= 8) return "favourite";
        if (pleasure >= 5) return "good";
        return "so-so";
    }
    // file support
    public String toFileLine() {
        return hobby.replace(";", ",") + ";" + classes + ";"
                + category.replace(";", ",") + ";" + pleasure;
    }
    public static Set fromFileLine(String line) {
        String[] p = line.split(";");
        if (p.length < 4) return null;
        try {
            return new Set(p[0], Integer.parseInt(p[1].trim()), p[2], Integer.parseInt(p[3].trim()));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}