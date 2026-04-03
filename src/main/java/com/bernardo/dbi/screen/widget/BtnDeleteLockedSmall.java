package com.bernardo.dbi.screen.widget;

public class BtnDeleteLockedSmall extends IconButton {
    public BtnDeleteLockedSmall() {
        super(
            10, 10,
            220, 0,
            10, 10,
            220, 0
        );
        setLocked(true);
    }
}
