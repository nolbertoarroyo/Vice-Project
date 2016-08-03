package com.test.vice20.Models;

/**
 * Created by kitty on 8/1/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Item {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("preview")
    @Expose
    private String preview;
    @SerializedName("tags")
    @Expose
    private List<String> tags = new ArrayList<String>();
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("feedText")
    @Expose
    private String feedText;
    @SerializedName("seriesTitle")
    @Expose
    private String seriesTitle;
    @SerializedName("seriesDescription")
    @Expose
    private Object seriesDescription;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("seriesId")
    @Expose
    private String seriesId;
    @SerializedName("episodeNumber")
    @Expose
    private Object episodeNumber;
    @SerializedName("part")
    @Expose
    private Object part;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("pubDate")
    @Expose
    private String pubDate;
    @SerializedName("pubTimestamp")
    @Expose
    private Integer pubTimestamp;
    @SerializedName("category")
    @Expose
    private String category;
//    @SerializedName("contributor")
//    @Expose
//    private Contributor contributor;
    @SerializedName("nsfw")
    @Expose
    private Boolean nsfw;
    @SerializedName("nsfb")
    @Expose
    private Boolean nsfb;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("thumb_10_4")
    @Expose
    private String thumb104;
    @SerializedName("thumb_10_3")
    @Expose
    private String thumb103;
    @SerializedName("thumb_16_9")
    @Expose
    private String thumb169;
    @SerializedName("thumb_7_10")
    @Expose
    private String thumb710;
    @SerializedName("media")
    @Expose
    private Media media;

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The preview
     */
    public String getPreview() {
        return preview;
    }

    /**
     *
     * @param preview
     * The preview
     */
    public void setPreview(String preview) {
        this.preview = preview;
    }

    /**
     *
     * @return
     * The tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     * The tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     *
     * @return
     * The body
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     * The feedText
     */
    public String getFeedText() {
        return feedText;
    }

    /**
     *
     * @param feedText
     * The feedText
     */
    public void setFeedText(String feedText) {
        this.feedText = feedText;
    }

    /**
     *
     * @return
     * The seriesTitle
     */
    public String getSeriesTitle() {
        return seriesTitle;
    }

    /**
     *
     * @param seriesTitle
     * The seriesTitle
     */
    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    /**
     *
     * @return
     * The seriesDescription
     */
    public Object getSeriesDescription() {
        return seriesDescription;
    }

    /**
     *
     * @param seriesDescription
     * The seriesDescription
     */
    public void setSeriesDescription(Object seriesDescription) {
        this.seriesDescription = seriesDescription;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The seriesId
     */
    public String getSeriesId() {
        return seriesId;
    }

    /**
     *
     * @param seriesId
     * The seriesId
     */
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    /**
     *
     * @return
     * The episodeNumber
     */
    public Object getEpisodeNumber() {
        return episodeNumber;
    }

    /**
     *
     * @param episodeNumber
     * The episodeNumber
     */
    public void setEpisodeNumber(Object episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    /**
     *
     * @return
     * The part
     */
    public Object getPart() {
        return part;
    }

    /**
     *
     * @param part
     * The part
     */
    public void setPart(Object part) {
        this.part = part;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     *
     * @param pubDate
     * The pubDate
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @return
     * The pubTimestamp
     */
    public Integer getPubTimestamp() {
        return pubTimestamp;
    }

    /**
     *
     * @param pubTimestamp
     * The pubTimestamp
     */
    public void setPubTimestamp(Integer pubTimestamp) {
        this.pubTimestamp = pubTimestamp;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
//     *
//     * @return
//     * The contributor
//     */
//    public Contributor getContributor() {
//        return contributor;
//    }
//
//    /**
//     *
//     * @param contributor
//     * The contributor
//     */
//    public void setContributor(Contributor contributor) {
//        this.contributor = contributor;
//    }

    /**
     *
     * @return
     * The nsfw
     */
    public Boolean getNsfw() {
        return nsfw;
    }

    /**
     *
     * @param nsfw
     * The nsfw
     */
    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    /**
     *
     * @return
     * The nsfb
     */
    public Boolean getNsfb() {
        return nsfb;
    }

    /**
     *
     * @param nsfb
     * The nsfb
     */
    public void setNsfb(Boolean nsfb) {
        this.nsfb = nsfb;
    }

    /**
     *
     * @return
     * The thumb
     */
    public String getThumb() {
        return thumb;
    }

    /**
     *
     * @param thumb
     * The thumb
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The thumb104
     */
    public String getThumb104() {
        return thumb104;
    }

    /**
     *
     * @param thumb104
     * The thumb_10_4
     */
    public void setThumb104(String thumb104) {
        this.thumb104 = thumb104;
    }

    /**
     *
     * @return
     * The thumb103
     */
    public String getThumb103() {
        return thumb103;
    }

    /**
     *
     * @param thumb103
     * The thumb_10_3
     */
    public void setThumb103(String thumb103) {
        this.thumb103 = thumb103;
    }

    /**
     *
     * @return
     * The thumb169
     */
    public String getThumb169() {
        return thumb169;
    }

    /**
     *
     * @param thumb169
     * The thumb_16_9
     */
    public void setThumb169(String thumb169) {
        this.thumb169 = thumb169;
    }

    /**
     *
     * @return
     * The thumb710
     */
    public String getThumb710() {
        return thumb710;
    }

    /**
     *
     * @param thumb710
     * The thumb_7_10
     */
    public void setThumb710(String thumb710) {
        this.thumb710 = thumb710;
    }

    /**
     *
     * @return
     * The media
     */
    public Media getMedia() {
        return media;
    }

    /**
     *
     * @param media
     * The media
     */
    public void setMedia(Media media) {
        this.media = media;
    }
}
