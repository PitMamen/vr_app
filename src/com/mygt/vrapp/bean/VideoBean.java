package com.mygt.vrapp.bean;

import java.util.List;

/**
 * Created by Richie on 2017/5/5.
 */


//第一个接口
public class VideoBean {

    /**
     * response : {"choiceVideo":[{"classification":"JZMJ","collectnumber":0,"description":"测试视频002描述","downloadnumber":0,"duration":"00:10:18","id":"10010020170427142334317002","labels":"3D,4K","laudnumber":0,"playnumber":3,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274176499038734.jpg","videoname":"测试视频002","year":"2017"},{"classification":"XGMN","collectnumber":0,"description":"测试视频001描述..","downloadnumber":0,"duration":"00:03:52","id":"10010020170427142230865001","labels":"3D,4K","laudnumber":0,"playnumber":1,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274105477077397.jpg","videoname":"测试视频001","year":"2017"},{"classification":"YXSP","collectnumber":0,"description":"测试视频004描述","downloadnumber":0,"duration":"00:01:35","id":"10010020170427142600033006","labels":"3D,4K","laudnumber":0,"playnumber":0,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274314068080656.jpg","videoname":"测试视频004","year":"2017"}],"playVideo":[{"classification":"JZMJ","collectnumber":0,"description":"测试视频002描述","downloadnumber":0,"duration":"00:10:18","id":"10010020170427142334317002","labels":"3D,4K","laudnumber":0,"playnumber":3,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274176499038734.jpg","videoname":"测试视频002","year":"2017"},{"classification":"XGMN","collectnumber":0,"description":"测试视频001描述..","downloadnumber":0,"duration":"00:03:52","id":"10010020170427142230865001","labels":"3D,4K","laudnumber":0,"playnumber":1,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274105477077397.jpg","videoname":"测试视频001","year":"2017"},{"classification":"WYZY","collectnumber":0,"description":"测试视频003描述","downloadnumber":0,"duration":"00:10:03","id":"10010020170427142442818003","labels":"3D,4K","laudnumber":0,"playnumber":1,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274241702059104.jpg","videoname":"测试视频003","year":"2017"}],"imagePrefix":"http://192.168.2.46:8088/","choiceGame":[{"apksize":"134040087","descinfo":"测试游戏003描述","downloadnumber":0,"gamename":"测试游戏003","icon":"/ueditor/jsp/upload/image/20170427/1493275824776081057.jpg","id":"10010020170427145052604005","lables":"HEAD_CONTROL,DOUBLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275832535090993.jpg","type":"GAME_PUZZLE","version":"1.0.1"},{"apksize":"134040087","descinfo":"测试游戏004描述","downloadnumber":0,"gamename":"测试游戏004","icon":"/ueditor/jsp/upload/image/20170427/1493275871316082906.jpg","id":"10010020170427145145985006","lables":"SINGLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275884798030810.jpg","type":"GAME_LEISURE","version":"1.0.3"},{"apksize":"134040087","descinfo":"测试游戏005描述","downloadnumber":0,"gamename":"测试游戏005","icon":"/ueditor/jsp/upload/image/20170427/1493275935625007337.jpg","id":"10010020170427145252937007","lables":"HEAD_CONTROL,SINGLE_HANDLE,ANDROID_3.0","publicity":"/ueditor/jsp/upload/image/20170427/1493275943383072828.jpg","type":"GAME_ACTION","version":"1.0.2"},{"apksize":"134040087","descinfo":"测试游戏002描述","downloadnumber":0,"gamename":"测试游戏002","icon":"/ueditor/jsp/upload/image/20170427/1493275739938091145.jpg","id":"10010020170427144932612002","lables":"HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275747824006164.jpg","type":"GAME_RACING","version":"1.0.0"}],"downGame":[{"apksize":"134040087","descinfo":"测试游戏001描述","downloadnumber":0,"gamename":"测试游戏001","icon":"/ueditor/jsp/upload/image/20170427/1493275479807051262.jpg","id":"10010020170427144834724001","lables":"HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275490227016536.jpg","type":"GAME_SPORTS","version":"1.0.0"},{"apksize":"134040087","descinfo":"测试游戏002描述","downloadnumber":0,"gamename":"测试游戏002","icon":"/ueditor/jsp/upload/image/20170427/1493275739938091145.jpg","id":"10010020170427144932612002","lables":"HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275747824006164.jpg","type":"GAME_RACING","version":"1.0.0"},{"apksize":"134040087","descinfo":"测试游戏003描述","downloadnumber":0,"gamename":"测试游戏003","icon":"/ueditor/jsp/upload/image/20170427/1493275824776081057.jpg","id":"10010020170427145052604005","lables":"HEAD_CONTROL,DOUBLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275832535090993.jpg","type":"GAME_PUZZLE","version":"1.0.1"}],"advertis":[{"business":"{id:\"10010020170427142230865001\"}","id":"10010020170427150222531005","image":"/ueditor/jsp/upload/image/20170427/1493276412983035366.jpg","sort":1,"title":"首页轮播广告","type":"YS","url":"/video/videoById.do"},{"business":"{id:\"10010020170427145052604005\"}","id":"10010020170427150335313006","image":"/ueditor/jsp/upload/image/20170427/1493276562988089715.jpg","sort":2,"title":"首页轮播广告","type":"YX","url":"/game/findByid.do"}]}
     * status : success
     */

    private ResponseBean response;
    private String status;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResponseBean {
        /**
         * choiceVideo : [{"classification":"JZMJ","collectnumber":0,"description":"测试视频002描述","downloadnumber":0,"duration":"00:10:18","id":"10010020170427142334317002","labels":"3D,4K","laudnumber":0,"playnumber":3,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274176499038734.jpg","videoname":"测试视频002","year":"2017"},{"classification":"XGMN","collectnumber":0,"description":"测试视频001描述..","downloadnumber":0,"duration":"00:03:52","id":"10010020170427142230865001","labels":"3D,4K","laudnumber":0,"playnumber":1,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274105477077397.jpg","videoname":"测试视频001","year":"2017"},{"classification":"YXSP","collectnumber":0,"description":"测试视频004描述","downloadnumber":0,"duration":"00:01:35","id":"10010020170427142600033006","labels":"3D,4K","laudnumber":0,"playnumber":0,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274314068080656.jpg","videoname":"测试视频004","year":"2017"}]
         * playVideo : [{"classification":"JZMJ","collectnumber":0,"description":"测试视频002描述","downloadnumber":0,"duration":"00:10:18","id":"10010020170427142334317002","labels":"3D,4K","laudnumber":0,"playnumber":3,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274176499038734.jpg","videoname":"测试视频002","year":"2017"},{"classification":"XGMN","collectnumber":0,"description":"测试视频001描述..","downloadnumber":0,"duration":"00:03:52","id":"10010020170427142230865001","labels":"3D,4K","laudnumber":0,"playnumber":1,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274105477077397.jpg","videoname":"测试视频001","year":"2017"},{"classification":"WYZY","collectnumber":0,"description":"测试视频003描述","downloadnumber":0,"duration":"00:10:03","id":"10010020170427142442818003","labels":"3D,4K","laudnumber":0,"playnumber":1,"thumbnails":"/ueditor/jsp/upload/image/20170427/1493274241702059104.jpg","videoname":"测试视频003","year":"2017"}]
         * imagePrefix : http://192.168.2.46:8088/
         * choiceGame : [{"apksize":"134040087","descinfo":"测试游戏003描述","downloadnumber":0,"gamename":"测试游戏003","icon":"/ueditor/jsp/upload/image/20170427/1493275824776081057.jpg","id":"10010020170427145052604005","lables":"HEAD_CONTROL,DOUBLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275832535090993.jpg","type":"GAME_PUZZLE","version":"1.0.1"},{"apksize":"134040087","descinfo":"测试游戏004描述","downloadnumber":0,"gamename":"测试游戏004","icon":"/ueditor/jsp/upload/image/20170427/1493275871316082906.jpg","id":"10010020170427145145985006","lables":"SINGLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275884798030810.jpg","type":"GAME_LEISURE","version":"1.0.3"},{"apksize":"134040087","descinfo":"测试游戏005描述","downloadnumber":0,"gamename":"测试游戏005","icon":"/ueditor/jsp/upload/image/20170427/1493275935625007337.jpg","id":"10010020170427145252937007","lables":"HEAD_CONTROL,SINGLE_HANDLE,ANDROID_3.0","publicity":"/ueditor/jsp/upload/image/20170427/1493275943383072828.jpg","type":"GAME_ACTION","version":"1.0.2"},{"apksize":"134040087","descinfo":"测试游戏002描述","downloadnumber":0,"gamename":"测试游戏002","icon":"/ueditor/jsp/upload/image/20170427/1493275739938091145.jpg","id":"10010020170427144932612002","lables":"HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275747824006164.jpg","type":"GAME_RACING","version":"1.0.0"}]
         * downGame : [{"apksize":"134040087","descinfo":"测试游戏001描述","downloadnumber":0,"gamename":"测试游戏001","icon":"/ueditor/jsp/upload/image/20170427/1493275479807051262.jpg","id":"10010020170427144834724001","lables":"HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275490227016536.jpg","type":"GAME_SPORTS","version":"1.0.0"},{"apksize":"134040087","descinfo":"测试游戏002描述","downloadnumber":0,"gamename":"测试游戏002","icon":"/ueditor/jsp/upload/image/20170427/1493275739938091145.jpg","id":"10010020170427144932612002","lables":"HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275747824006164.jpg","type":"GAME_RACING","version":"1.0.0"},{"apksize":"134040087","descinfo":"测试游戏003描述","downloadnumber":0,"gamename":"测试游戏003","icon":"/ueditor/jsp/upload/image/20170427/1493275824776081057.jpg","id":"10010020170427145052604005","lables":"HEAD_CONTROL,DOUBLE_HANDLE,ANDROID_2.3","publicity":"/ueditor/jsp/upload/image/20170427/1493275832535090993.jpg","type":"GAME_PUZZLE","version":"1.0.1"}]
         * advertis : [{"business":"{id:\"10010020170427142230865001\"}","id":"10010020170427150222531005","image":"/ueditor/jsp/upload/image/20170427/1493276412983035366.jpg","sort":1,"title":"首页轮播广告","type":"YS","url":"/video/videoById.do"},{"business":"{id:\"10010020170427145052604005\"}","id":"10010020170427150335313006","image":"/ueditor/jsp/upload/image/20170427/1493276562988089715.jpg","sort":2,"title":"首页轮播广告","type":"YX","url":"/game/findByid.do"}]
         */

        private String imagePrefix;
        private List<ChoiceVideoBean> choiceVideo;
        private List<PlayVideoBean> playVideo;
        private List<ChoiceGameBean> choiceGame;
        private List<DownGameBean> downGame;
        private List<AdvertisBean> advertis;

        public String getImagePrefix() {
            return imagePrefix;
        }

        public void setImagePrefix(String imagePrefix) {
            this.imagePrefix = imagePrefix;
        }

        public List<ChoiceVideoBean> getChoiceVideo() {
            return choiceVideo;
        }

        public void setChoiceVideo(List<ChoiceVideoBean> choiceVideo) {
            this.choiceVideo = choiceVideo;
        }

        public List<PlayVideoBean> getPlayVideo() {
            return playVideo;
        }

        public void setPlayVideo(List<PlayVideoBean> playVideo) {
            this.playVideo = playVideo;
        }

        public List<ChoiceGameBean> getChoiceGame() {
            return choiceGame;
        }

        public void setChoiceGame(List<ChoiceGameBean> choiceGame) {
            this.choiceGame = choiceGame;
        }

        public List<DownGameBean> getDownGame() {
            return downGame;
        }

        public void setDownGame(List<DownGameBean> downGame) {
            this.downGame = downGame;
        }

        public List<AdvertisBean> getAdvertis() {
            return advertis;
        }

        public void setAdvertis(List<AdvertisBean> advertis) {
            this.advertis = advertis;
        }

        public static class ChoiceVideoBean {
            /**
             * classification : JZMJ
             * collectnumber : 0
             * description : 测试视频002描述
             * downloadnumber : 0
             * duration : 00:10:18
             * id : 10010020170427142334317002
             * labels : 3D,4K
             * laudnumber : 0
             * playnumber : 3
             * thumbnails : /ueditor/jsp/upload/image/20170427/1493274176499038734.jpg
             * videoname : 测试视频002
             * year : 2017
             */

            private String classification;
            private int collectnumber;
            private String description;
            private int downloadnumber;
            private String duration;
            private String id;
            private String labels;
            private int laudnumber;
            private int playnumber;
            private String thumbnails;
            private String videoname;
            private String year;

            public String getClassification() {
                return classification;
            }

            public void setClassification(String classification) {
                this.classification = classification;
            }

            public int getCollectnumber() {
                return collectnumber;
            }

            public void setCollectnumber(int collectnumber) {
                this.collectnumber = collectnumber;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getDownloadnumber() {
                return downloadnumber;
            }

            public void setDownloadnumber(int downloadnumber) {
                this.downloadnumber = downloadnumber;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLabels() {
                return labels;
            }

            public void setLabels(String labels) {
                this.labels = labels;
            }

            public int getLaudnumber() {
                return laudnumber;
            }

            public void setLaudnumber(int laudnumber) {
                this.laudnumber = laudnumber;
            }

            public int getPlaynumber() {
                return playnumber;
            }

            public void setPlaynumber(int playnumber) {
                this.playnumber = playnumber;
            }

            public String getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(String thumbnails) {
                this.thumbnails = thumbnails;
            }

            public String getVideoname() {
                return videoname;
            }

            public void setVideoname(String videoname) {
                this.videoname = videoname;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }
        }

        public static class PlayVideoBean {
            /**
             * classification : JZMJ
             * collectnumber : 0
             * description : 测试视频002描述
             * downloadnumber : 0
             * duration : 00:10:18
             * id : 10010020170427142334317002
             * labels : 3D,4K
             * laudnumber : 0
             * playnumber : 3
             * thumbnails : /ueditor/jsp/upload/image/20170427/1493274176499038734.jpg
             * videoname : 测试视频002
             * year : 2017
             */

            private String classification;
            private int collectnumber;
            private String description;
            private int downloadnumber;
            private String duration;
            private String id;
            private String labels;
            private int laudnumber;
            private int playnumber;
            private String thumbnails;
            private String videoname;
            private String year;

            public String getClassification() {
                return classification;
            }

            public void setClassification(String classification) {
                this.classification = classification;
            }

            public int getCollectnumber() {
                return collectnumber;
            }

            public void setCollectnumber(int collectnumber) {
                this.collectnumber = collectnumber;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getDownloadnumber() {
                return downloadnumber;
            }

            public void setDownloadnumber(int downloadnumber) {
                this.downloadnumber = downloadnumber;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLabels() {
                return labels;
            }

            public void setLabels(String labels) {
                this.labels = labels;
            }

            public int getLaudnumber() {
                return laudnumber;
            }

            public void setLaudnumber(int laudnumber) {
                this.laudnumber = laudnumber;
            }

            public int getPlaynumber() {
                return playnumber;
            }

            public void setPlaynumber(int playnumber) {
                this.playnumber = playnumber;
            }

            public String getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(String thumbnails) {
                this.thumbnails = thumbnails;
            }

            public String getVideoname() {
                return videoname;
            }

            public void setVideoname(String videoname) {
                this.videoname = videoname;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }
        }

        public static class ChoiceGameBean {
            /**
             * apksize : 134040087
             * descinfo : 测试游戏003描述
             * downloadnumber : 0
             * gamename : 测试游戏003
             * icon : /ueditor/jsp/upload/image/20170427/1493275824776081057.jpg
             * id : 10010020170427145052604005
             * lables : HEAD_CONTROL,DOUBLE_HANDLE,ANDROID_2.3
             * publicity : /ueditor/jsp/upload/image/20170427/1493275832535090993.jpg
             * type : GAME_PUZZLE
             * version : 1.0.1
             */

            private String apksize;
            private String descinfo;
            private int downloadnumber;
            private String gamename;
            private String icon;
            private String id;
            private String lables;
            private String publicity;
            private String type;
            private String version;

            public String getApksize() {
                return apksize;
            }

            public void setApksize(String apksize) {
                this.apksize = apksize;
            }

            public String getDescinfo() {
                return descinfo;
            }

            public void setDescinfo(String descinfo) {
                this.descinfo = descinfo;
            }

            public int getDownloadnumber() {
                return downloadnumber;
            }

            public void setDownloadnumber(int downloadnumber) {
                this.downloadnumber = downloadnumber;
            }

            public String getGamename() {
                return gamename;
            }

            public void setGamename(String gamename) {
                this.gamename = gamename;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLables() {
                return lables;
            }

            public void setLables(String lables) {
                this.lables = lables;
            }

            public String getPublicity() {
                return publicity;
            }

            public void setPublicity(String publicity) {
                this.publicity = publicity;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }

        public static class DownGameBean {
            /**
             * apksize : 134040087
             * descinfo : 测试游戏001描述
             * downloadnumber : 0
             * gamename : 测试游戏001
             * icon : /ueditor/jsp/upload/image/20170427/1493275479807051262.jpg
             * id : 10010020170427144834724001
             * lables : HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3
             * publicity : /ueditor/jsp/upload/image/20170427/1493275490227016536.jpg
             * type : GAME_SPORTS
             * version : 1.0.0
             */

            private String apksize;
            private String descinfo;
            private int downloadnumber;
            private String gamename;
            private String icon;
            private String id;
            private String lables;
            private String publicity;
            private String type;
            private String version;

            public String getApksize() {
                return apksize;
            }

            public void setApksize(String apksize) {
                this.apksize = apksize;
            }

            public String getDescinfo() {
                return descinfo;
            }

            public void setDescinfo(String descinfo) {
                this.descinfo = descinfo;
            }

            public int getDownloadnumber() {
                return downloadnumber;
            }

            public void setDownloadnumber(int downloadnumber) {
                this.downloadnumber = downloadnumber;
            }

            public String getGamename() {
                return gamename;
            }

            public void setGamename(String gamename) {
                this.gamename = gamename;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLables() {
                return lables;
            }

            public void setLables(String lables) {
                this.lables = lables;
            }

            public String getPublicity() {
                return publicity;
            }

            public void setPublicity(String publicity) {
                this.publicity = publicity;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }

        public static class AdvertisBean {
            /**
             * business : {id:"10010020170427142230865001"}
             * id : 10010020170427150222531005
             * image : /ueditor/jsp/upload/image/20170427/1493276412983035366.jpg
             * sort : 1
             * title : 首页轮播广告
             * type : YS
             * url : /video/videoById.do
             */

            private String business;
            private String id;
            private String image;
            private int sort;
            private String title;
            private String type;
            private String url;

            public String getBusiness() {
                return business;
            }

            public void setBusiness(String business) {
                this.business = business;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }


    /***
     *  bejson格式化后的
     *
     *
     *  {
     "response": {
     "choiceVideo": [
     {
     "classification": "JZMJ",
     "collectnumber": 0,
     "description": "测试视频002描述",
     "downloadnumber": 0,
     "duration": "00:10:18",
     "id": "10010020170427142334317002",
     "labels": "3D,4K",
     "laudnumber": 0,
     "playnumber": 3,
     "thumbnails": "/ueditor/jsp/upload/image/20170427/1493274176499038734.jpg",
     "videoname": "测试视频002",
     "year": "2017"
     },
     {
     "classification": "XGMN",
     "collectnumber": 0,
     "description": "测试视频001描述..",
     "downloadnumber": 0,
     "duration": "00:03:52",
     "id": "10010020170427142230865001",
     "labels": "3D,4K",
     "laudnumber": 0,
     "playnumber": 1,
     "thumbnails": "/ueditor/jsp/upload/image/20170427/1493274105477077397.jpg",
     "videoname": "测试视频001",
     "year": "2017"
     },
     {
     "classification": "YXSP",
     "collectnumber": 0,
     "description": "测试视频004描述",
     "downloadnumber": 0,
     "duration": "00:01:35",
     "id": "10010020170427142600033006",
     "labels": "3D,4K",
     "laudnumber": 0,
     "playnumber": 0,
     "thumbnails": "/ueditor/jsp/upload/image/20170427/1493274314068080656.jpg",
     "videoname": "测试视频004",
     "year": "2017"
     }
     ],
     "playVideo": [
     {
     "classification": "JZMJ",
     "collectnumber": 0,
     "description": "测试视频002描述",
     "downloadnumber": 0,
     "duration": "00:10:18",
     "id": "10010020170427142334317002",
     "labels": "3D,4K",
     "laudnumber": 0,
     "playnumber": 3,
     "thumbnails": "/ueditor/jsp/upload/image/20170427/1493274176499038734.jpg",
     "videoname": "测试视频002",
     "year": "2017"
     },
     {
     "classification": "XGMN",
     "collectnumber": 0,
     "description": "测试视频001描述..",
     "downloadnumber": 0,
     "duration": "00:03:52",
     "id": "10010020170427142230865001",
     "labels": "3D,4K",
     "laudnumber": 0,
     "playnumber": 1,
     "thumbnails": "/ueditor/jsp/upload/image/20170427/1493274105477077397.jpg",
     "videoname": "测试视频001",
     "year": "2017"
     },
     {
     "classification": "WYZY",
     "collectnumber": 0,
     "description": "测试视频003描述",
     "downloadnumber": 0,
     "duration": "00:10:03",
     "id": "10010020170427142442818003",
     "labels": "3D,4K",
     "laudnumber": 0,
     "playnumber": 1,
     "thumbnails": "/ueditor/jsp/upload/image/20170427/1493274241702059104.jpg",
     "videoname": "测试视频003",
     "year": "2017"
     }
     ],
     "imagePrefix": "http://192.168.2.46:8088/",
     "choiceGame": [
     {
     "apksize": "134040087",
     "descinfo": "测试游戏003描述",
     "downloadnumber": 0,
     "gamename": "测试游戏003",
     "icon": "/ueditor/jsp/upload/image/20170427/1493275824776081057.jpg",
     "id": "10010020170427145052604005",
     "lables": "HEAD_CONTROL,DOUBLE_HANDLE,ANDROID_2.3",
     "publicity": "/ueditor/jsp/upload/image/20170427/1493275832535090993.jpg",
     "type": "GAME_PUZZLE",
     "version": "1.0.1"
     },
     {
     "apksize": "134040087",
     "descinfo": "测试游戏004描述",
     "downloadnumber": 0,
     "gamename": "测试游戏004",
     "icon": "/ueditor/jsp/upload/image/20170427/1493275871316082906.jpg",
     "id": "10010020170427145145985006",
     "lables": "SINGLE_HANDLE,ANDROID_2.3",
     "publicity": "/ueditor/jsp/upload/image/20170427/1493275884798030810.jpg",
     "type": "GAME_LEISURE",
     "version": "1.0.3"
     },
     {
     "apksize": "134040087",
     "descinfo": "测试游戏005描述",
     "downloadnumber": 0,
     "gamename": "测试游戏005",
     "icon": "/ueditor/jsp/upload/image/20170427/1493275935625007337.jpg",
     "id": "10010020170427145252937007",
     "lables": "HEAD_CONTROL,SINGLE_HANDLE,ANDROID_3.0",
     "publicity": "/ueditor/jsp/upload/image/20170427/1493275943383072828.jpg",
     "type": "GAME_ACTION",
     "version": "1.0.2"
     },
     {
     "apksize": "134040087",
     "descinfo": "测试游戏002描述",
     "downloadnumber": 0,
     "gamename": "测试游戏002",
     "icon": "/ueditor/jsp/upload/image/20170427/1493275739938091145.jpg",
     "id": "10010020170427144932612002",
     "lables": "HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3",
     "publicity": "/ueditor/jsp/upload/image/20170427/1493275747824006164.jpg",
     "type": "GAME_RACING",
     "version": "1.0.0"
     }
     ],
     "downGame": [
     {
     "apksize": "134040087",
     "descinfo": "测试游戏001描述",
     "downloadnumber": 0,
     "gamename": "测试游戏001",
     "icon": "/ueditor/jsp/upload/image/20170427/1493275479807051262.jpg",
     "id": "10010020170427144834724001",
     "lables": "HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3",
     "publicity": "/ueditor/jsp/upload/image/20170427/1493275490227016536.jpg",
     "type": "GAME_SPORTS",
     "version": "1.0.0"
     },
     {
     "apksize": "134040087",
     "descinfo": "测试游戏002描述",
     "downloadnumber": 0,
     "gamename": "测试游戏002",
     "icon": "/ueditor/jsp/upload/image/20170427/1493275739938091145.jpg",
     "id": "10010020170427144932612002",
     "lables": "HEAD_CONTROL,SINGLE_HANDLE,ANDROID_2.3",
     "publicity": "/ueditor/jsp/upload/image/20170427/1493275747824006164.jpg",
     "type": "GAME_RACING",
     "version": "1.0.0"
     },
     {
     "apksize": "134040087",
     "descinfo": "测试游戏003描述",
     "downloadnumber": 0,
     "gamename": "测试游戏003",
     "icon": "/ueditor/jsp/upload/image/20170427/1493275824776081057.jpg",
     "id": "10010020170427145052604005",
     "lables": "HEAD_CONTROL,DOUBLE_HANDLE,ANDROID_2.3",
     "publicity": "/ueditor/jsp/upload/image/20170427/1493275832535090993.jpg",
     "type": "GAME_PUZZLE",
     "version": "1.0.1"
     }
     ],
     "advertis": [
     {
     "business": "{id:\"10010020170427142230865001\"}",
     "id": "10010020170427150222531005",
     "image": "/ueditor/jsp/upload/image/20170427/1493276412983035366.jpg",
     "sort": 1,
     "title": "首页轮播广告",
     "type": "YS",
     "url": "/video/videoById.do"
     },
     {
     "business": "{id:\"10010020170427145052604005\"}",
     "id": "10010020170427150335313006",
     "image": "/ueditor/jsp/upload/image/20170427/1493276562988089715.jpg",
     "sort": 2,
     "title": "首页轮播广告",
     "type": "YX",
     "url": "/game/findByid.do"
     }
     ]
     },
     "status": "success"
     }
     *
     */


}
