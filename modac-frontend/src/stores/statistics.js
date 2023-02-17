import { defineStore } from "pinia";
import { ref } from "vue";
import statistics from "../api/statistics";

export const useStatsStore = defineStore("statistics", () => {
  // status (변수)
    const barData = ref([])
    const radarData = ref([])
    const doughnutData = ref([])


    const api = statistics;


  // action (API)
  // 단체 채팅방에서는 DB에 저장하지 않아서 API 사용X

  return { 
    api,
    barData,
    radarData,
    doughnutData,
  };
},
{
  persist: true,
}
);
