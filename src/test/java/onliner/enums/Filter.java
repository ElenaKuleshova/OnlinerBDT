package onliner.enums;

public enum Filter {
    PRODUCER("Производитель"),
    MAXPRICE("до"),
    RESOLUTION("Разрешение"),
    MINDIAGONAL("Диагональ"),
    MAXDIAGONAL("Диагональ");

    private String value;

    Filter(final String defaultValue){
      value = defaultValue;
    }

    public String makeString(){
      return value;
    }
}
