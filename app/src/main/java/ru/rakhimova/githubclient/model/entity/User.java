package ru.rakhimova.githubclient.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    @SerializedName("login")
    private String login;

    @Expose
    @SerializedName("id")
    private float id;

    @Expose
    @SerializedName("avatar_url")
    private String avatarUrl;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("bio")
    private String bio;

    @Expose
    @SerializedName("public_repos")
    private float publicRepos;

    @Expose
    @SerializedName("public_gists")
    private float publicGists;

    @Expose
    @SerializedName("followers")
    private float followers;

    @Expose
    @SerializedName("following")
    private float following;

    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Expose
    @SerializedName("updated_at")
    private String updatedAt;

    @Expose
    @SerializedName("node_id")
    private String nodeId;

    @Expose
    @SerializedName("gravatar_id")
    private String gravatarId;

    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("html_url")
    private String htmlUrl;

    @Expose
    @SerializedName("followers_url")
    private String followersUrl;

    @Expose
    @SerializedName("following_url")
    private String followingUrl;

    @Expose
    @SerializedName("gists_url")
    private String gistsUrl;

    @Expose
    @SerializedName("starred_url")
    private String starredUrl;

    @Expose
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @Expose
    @SerializedName("organizations_url")
    private String organizationsUrl;

    @Expose
    @SerializedName("repos_url")
    private String reposUrl;

    @Expose
    @SerializedName("events_url")
    private String eventsUrl;

    @Expose
    @SerializedName("received_events_url")
    private String receivedEventsUrl;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("site_admin")
    private boolean siteAdmin;

    @Expose
    @SerializedName("company")
    private String company;

    @Expose
    @SerializedName("blog")
    private String blog;

    @Expose
    @SerializedName("location")
    private String location;

    @Expose
    @SerializedName("hireable")
    private boolean hireable;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public float getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(float publicRepos) {
        this.publicRepos = publicRepos;
    }

    public float getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(float publicGists) {
        this.publicGists = publicGists;
    }

    public float getFollowers() {
        return followers;
    }

    public void setFollowers(float followers) {
        this.followers = followers;
    }

    public float getFollowing() {
        return following;
    }

    public void setFollowing(float following) {
        this.following = following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isHireable() {
        return hireable;
    }

    public void setHireable(boolean hireable) {
        this.hireable = hireable;
    }
}
