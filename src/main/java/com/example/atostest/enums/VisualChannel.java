package com.example.atostest.enums;

public enum VisualChannel {
    FILM(1),
    SERIE(2);

    private final long visualChannel;

    VisualChannel(long visualChannelId) {
        this.visualChannel = visualChannelId;
    }

    public long getVisualChannel() {
        return visualChannel;
    }
}
