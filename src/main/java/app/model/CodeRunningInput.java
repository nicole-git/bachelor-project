package app.model;

import lombok.Data;

@Data
public class CodeRunningInput { //used for converting json to java
   private String exerciseId;
   private String language;
   private String code;
}
