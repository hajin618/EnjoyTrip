<template lang="">
    <div id="map">
    </div>
</template>

<script>
export default {
    name: "KakaoMap",
    data(){
        return{
            map: null,
            positions: [],
            markers: [],

            ChPositions: [],
            ChMarkers: [],
        };
    },
    created(){
        //console.log("kakao created attractions : " +this.attractions);
        //console.log("kakao created ChildAttractions : " +this.childAttractions);
    },
    props: {
        attractions: [],
        childAttractions: [],
    },
    watch: {
        attractions(){
            // console.log("attraction change", this.attractions);
            this.positions = [];
            this.attractions.forEach((attraction) => {
                let obj = {};
                obj.title = attraction.title;
                obj.latlng = new window.kakao.maps.LatLng(attraction.latitude, attraction.longitude);
                this.positions.push(obj);
            });
            this.loadMarker();
        },

        childAttractions(){
            this.ChPositions = [];
            this.childAttractions.forEach((attraction) => {
                let obj = {};
                obj.title = attraction.attraction_name;
                obj.latlng = new window.kakao.maps.LatLng(attraction.latitude, attraction.longitude);
                this.ChPositions.push(obj);
            });
            this.loadMarker();
        }
    },
    mounted() {
        if (window.kakao && window.kakao.maps) {
            // 카카오 객체가 있고, 카카오 맵그릴 준비가 되어 있다면 맵 실행
            this.loadMap();
        } else {
            // 없다면 카카오 스크립트 추가 후 맵 실행
            this.loadScript();
        }
    },
    methods: {
        loadScript() {
            const script = document.createElement("script");
            script.src =
                "//dapi.kakao.com/v2/maps/sdk.js?appkey=7b49f209072b38291c3376198f4f5a16&autoload=false"; // &autoload=false api를 로드한 후 맵을 그리는 함수가 실행되도록 구현
            script.onload = () => window.kakao.maps.load(this.loadMap); // 스크립트 로드가 끝나면 지도를 실행될 준비가 되어 있다면 지도가 실행되도록 구현

            document.head.appendChild(script); // html>head 안에 스크립트 소스를 추가
        },

        loadMap() {
            const container = document.getElementById("map"); // 지도를 담을 DOM 영역
            const options = {
                // 지도를 생성할 때 필요한 기본 옵션
                center: new window.kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3, // 지도의 레벨(확대, 축소 정도)
            };

            this.map = new window.kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

            // 지도 로드되면서 마커 생성
            //this.loadMarker();
        },

        loadMarker(){
            // console.log("load Marker start");
            // 현재 마커 제거
            this.deleteMarker();
            // 마커 표시 위치
            // const markerPosition = new window.kakao.maps.LatLng(
            //     33.450701, 126.570667
            // );

            // 마커 생성
            // const marker = new window.kakao.maps.Marker({
            //     position: markerPosition
            // })

            // 마커 생성
            this.markers = [];
            // console.log("positions: " + this.positions);
            this.positions.forEach((position) => {
                const marker = new window.kakao.maps.Marker({
                    map: this.map,
                    position: position.latlng,
                    title: position.title,
                });
                this.markers.push(marker);
            })

            // 어린이 여행지 마커 생성
            this.ChMarkers = [];
            this.ChPositions.forEach((position) => {
                const marker = new window.kakao.maps.Marker({
                    map : this.map,
                    position: position.latlng,
                    title: position.title,
                });
                this.ChMarkers.push(marker);
            })

            // // 마커 표시
            // marker.setMap(this.map);
            // 지도 이동시키기
            const bounds = this.positions.reduce(
                (bounds, position) => bounds.extend(position.latlng),
                new window.kakao.maps.LatLngBounds()
            );

            this.map.setBounds(bounds);
        },

        deleteMarker(){
            // console.log("delete marker start");
            if(this.markers.length > 0){
                this.markers.forEach((item) => {
                    console.log("item : " + item);
                    item.setMap(null);
                });
            }

            // if(this.ChMarkers.length > 0){
            //     this.ChMarkers.forEach((item) => {
            //         console.log("item : " + item);
            //         item.setMap(null);
            //     })
            // }
        },

    
    },
}
</script>

<style scoped>
#map{
    width: 100%;
    height: 100%;
}
</style>