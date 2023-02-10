export const barChartData = {
  type: "bar",
  data: {
    labels: ["월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"],
    datasets: [
      {
        label: "일주일 간 TIL 작성 횟수",
        type: "bar",
        data: [5, 6, 7, 4, 2, 1, 3],
        backgroundColor: 
          "rgba(54,73,93,.5)"      
        ,
        borderColor: "#36495d",
        borderWidth: 1,
      }
    ]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
    },
    scales: {
      yAxes: [{
        ticks: {
          min: 0
        }
      }]
    }
  },
};




export default barChartData;