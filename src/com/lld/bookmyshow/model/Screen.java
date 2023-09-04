package com.lld.bookmyshow.model;

import com.lld.bookmyshow.constant.AudioExperienceType;
import com.lld.bookmyshow.constant.VideoExperienceType;

public class Screen {
    private VideoExperienceType videoExperienceType;
    private AudioExperienceType audioExperienceType;

    public Screen(VideoExperienceType videoExperienceType, AudioExperienceType audioExperienceType) {
        this.videoExperienceType = videoExperienceType;
        this.audioExperienceType = audioExperienceType;
    }

    public VideoExperienceType getVideoExperienceType() {
        return videoExperienceType;
    }

    public void setVideoExperienceType(VideoExperienceType videoExperienceType) {
        this.videoExperienceType = videoExperienceType;
    }

    public AudioExperienceType getAudioExperienceType() {
        return audioExperienceType;
    }

    public void setAudioExperienceType(AudioExperienceType audioExperienceType) {
        this.audioExperienceType = audioExperienceType;
    }
}
