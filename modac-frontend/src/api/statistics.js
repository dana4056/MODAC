import http from "@/api/http";
import { useStatsStore } from '@/stores/statistics';
import { storeToRefs } from "pinia"

export default {
  getStatistics(usersSeq) {
    http.get(`/articles/statistics`, {
        params : {
            user : usersSeq
        }})
    .then((response) => {
        console.log("response.data",response.data)
        const code = response.status;
        const statsStore = useStatsStore();
        const { barData } = storeToRefs(statsStore);
        const { radarData } = storeToRefs(statsStore);
        const { doughnutData } = storeToRefs(statsStore);

        barData.value = response.data.daysOfWeekList
        radarData.value = response.data.categoriesList
        doughnutData.value = response.data.categoriesList
        console.log("gggg", barData.value[0])
        console.log("asdasd", barData.value[0].dayOfWeek)

        // barData.value = data.daysOfWeekList
        // radarData.value = data.categoriesList
        // doughnutData.value = data.categoriesList


        if(code == 200){
            console.log("통계 데이터: " + JSON.stringify(response.data));
        }
    })
    .catch((error) => {
        console.log("에러",error);
    })
  }
}