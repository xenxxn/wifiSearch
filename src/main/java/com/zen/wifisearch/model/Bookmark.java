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
    private int BM_ID;
    private int BG_ID;
    private int WF_ID;
    private Date BM_RG_DATE;
    private Wifi wifi;
    private BookmarkGroup bookmarkGroup;
}
