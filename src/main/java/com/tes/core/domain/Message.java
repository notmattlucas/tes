package com.tes.core.domain;

import com.tes.core.domain.Channel;

import java.util.Set;

public class Message {

    public Message(Set<Channel> channelSet, String body) {
        this.channelSet = channelSet;
        this.body = body;
    }

    private Set<Channel> channelSet;

    private String body;

    public Set<Channel> getChannelSet() {
        return channelSet;
    }

    public void setChannelSet(Set<Channel> channelSet) {
        this.channelSet = channelSet;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}