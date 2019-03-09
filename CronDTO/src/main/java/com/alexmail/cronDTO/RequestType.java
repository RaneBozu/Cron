package com.alexmail.cronDTO;

public enum RequestType {
    TRANSLATE{
        @Override
        public String getType() {
            return "translate";
        }
    },
    HISTORY {
        @Override
        public String getType() {
            return "history";
        }
    },
    UPDATE_HISTORY {
        @Override
        public String getType() {
            return "update_history";
        }
    };

    public abstract String getType();
}
