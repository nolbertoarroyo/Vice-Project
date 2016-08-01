package com.test.vice20.Models;

/**
 * Created by kitty on 8/1/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributor {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("slug")
    @Expose
    private Object slug;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("jobtitle")
    @Expose
    private Object jobtitle;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("bio")
    @Expose
    private Object bio;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("google_plus_url")
    @Expose
    private String googlePlusUrl;
    @SerializedName("image_path")
    @Expose
    private String imagePath;
    @SerializedName("image_file_name")
    @Expose
    private String imageFileName;
    @SerializedName("image_crop_path")
    @Expose
    private String imageCropPath;
    @SerializedName("image_width")
    @Expose
    private String imageWidth;
    @SerializedName("image_height")
    @Expose
    private String imageHeight;

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
     * The slug
     */
    public Object getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(Object slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     * The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     * The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     * The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     * The jobtitle
     */
    public Object getJobtitle() {
        return jobtitle;
    }

    /**
     *
     * @param jobtitle
     * The jobtitle
     */
    public void setJobtitle(Object jobtitle) {
        this.jobtitle = jobtitle;
    }

    /**
     *
     * @return
     * The twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     *
     * @param twitter
     * The twitter
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     *
     * @return
     * The facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     *
     * @param facebook
     * The facebook
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     *
     * @return
     * The bio
     */
    public Object getBio() {
        return bio;
    }

    /**
     *
     * @param bio
     * The bio
     */
    public void setBio(Object bio) {
        this.bio = bio;
    }

    /**
     *
     * @return
     * The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     * The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     *
     * @return
     * The googlePlusUrl
     */
    public String getGooglePlusUrl() {
        return googlePlusUrl;
    }

    /**
     *
     * @param googlePlusUrl
     * The google_plus_url
     */
    public void setGooglePlusUrl(String googlePlusUrl) {
        this.googlePlusUrl = googlePlusUrl;
    }

    /**
     *
     * @return
     * The imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     *
     * @param imagePath
     * The image_path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     *
     * @return
     * The imageFileName
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     *
     * @param imageFileName
     * The image_file_name
     */
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    /**
     *
     * @return
     * The imageCropPath
     */
    public String getImageCropPath() {
        return imageCropPath;
    }

    /**
     *
     * @param imageCropPath
     * The image_crop_path
     */
    public void setImageCropPath(String imageCropPath) {
        this.imageCropPath = imageCropPath;
    }

    /**
     *
     * @return
     * The imageWidth
     */
    public String getImageWidth() {
        return imageWidth;
    }

    /**
     *
     * @param imageWidth
     * The image_width
     */
    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     *
     * @return
     * The imageHeight
     */
    public String getImageHeight() {
        return imageHeight;
    }

    /**
     *
     * @param imageHeight
     * The image_height
     */
    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

}
