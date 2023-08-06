package com.zen.wifisearch.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bookmark {
    private int bmId;
    private String bmName;
    private int bmOrder;
    private Date bmRgDate;
    private Date bmMdDate;
}
