<template>
  <div class="app-road-archive">
    <h1 style="font-weight: 300; margin-bottom: 2rem;">Road Database</h1>

    <iframe
      src="https://www.google.com/maps/d/embed?mid=1rd6B6h9ZcbZo2Q8kwcYqaAt-ROuy1UP3"
      width="1740"
      height="480"
    ></iframe>
    <div v-show="!loaded">
      <div
        style="display: flex; width: 100%; justify-content: center; align-items: center; flex-direction: column; height: 20vh;"
      >
        <div class="spinner-border text-info mb-3" role="status">
          <span class="sr-only">Loading...</span>
        </div>
        <h3 style="font-weight: 300;">Loading please wait.</h3>
      </div>
    </div>
    <template v-if="loaded">
    <div class="roads-table">
      <h3 style="font-weight: 300; margin-bottom: 2rem;">Roads</h3>
      <div style="margin-bottom: 2rem;">
        <form class="form-inline">
          <label class="sr-only" for="inlineFormInputName2">Name</label>
          <input
            @change="(e) => {roadChange(e)}"
            type="text"
            class="form-control mr-sm-2"
            id="inlineFormInputName2"
            placeholder="Search By Road ID"
          />
          <select
            class="custom-select mr-2"
            id="inlineFormCustomSelectPref"
            @change="(e) => {distChange(e)}"
          >
            <option selected value="all">All</option>
            <option
              v-for="category in categories_l"
              :key="category[0].toString()"
              :value="category[0]"
            >{{ category[1] }}</option>
          </select>
        </form>
      </div>
      <table class="table table-bordered header-fixed" style="border: 1px solid gray;">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Road ID</th>
            <th scope="col">Road Name</th>
            <th scope="col">Road Type</th>
            <th scope="col">Districts</th>
            <th scope="col">Start</th>
            <th scope="col">End</th>
            <th scope="col">Length (km)</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="road in active" :key="road.id">
            <router-link
              tag="td"
              :to="{name: 'road', params: {id: road.road_id}}"
              style="cursor: pointer; text-decoration: underline;"
            >{{ road.road_id }}</router-link>
            <td>{{road.name}}</td>
            <td>{{categories[road.category]}}</td>
            <td>{{ districts[road.district]}}</td>
            <td>{{road.start}}</td>
            <td>{{road.end}}</td>
            <td>{{road.length.toFixed(2)}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="analytics">
      <h3 style="font-weight: 300; margin-bottom: 3rem;">Predicted Budget for Upcoming Months</h3>
      <div style="width: 700px;">
          <div style="display: flex;">
            <canvas id="barchart" ref="barchart"></canvas>
          </div>
        </div>
    </div>
    </template>
  </div>
</template>

<script>
import { district, categories } from "./enums";
import Chart from "chart.js";
export default {
  name: "road-archive",
  components: {},
  methods: {
    roadChange(e) {
      if (e.target.value === "") {
        this.active = this.roads;
        return;
      }
      this.active = this.roads.filter((complaint) => {
        return complaint.road_id == e.target.value;
      });
    },
    defectsByMonthBar() {
      new Chart(this.$refs.barchart, {
        type: "line",
        data: {
          labels: [
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
          ],
          datasets: [
            {
              data: this.costs,
              borderColor: "#FFCC00",
              backgroundColor: 'white',
              label: "Cost (₹)"
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Distribution by Months",
            fontSize: 16,
          },
        },
      });
    },
    distChange(e) {
      if (e.target.value === "all") {
        this.active = this.roads;
        return;
      }
      this.active = this.roads.filter((road) => {
        return road.category == e.target.value;
      });
    },
  },
  mounted() {
    this.axios
      .post("road", { op: "all_kp" })
      .then(({ data }) => {
        this.roads = data;
        this.active = data;
        this.loaded = true;

        this.axios
          .get("cost_pred")
          .then(({ data }) => {
            for(let i = 1; i <= 12; i++) {
              this.costs.push(data.cost[i]);
            }
            this.defectsByMonthBar();
            console.log(this.costs);
          })
          .catch((err) => {
            console.err(err);
          });
      })
      .catch((err) => {
        console.err(err);
      });
  },
  data() {
    return {
      roads: [],
      active: [],
      costs: [],
      loaded: false,
      districts: district,
      categories: categories,
      categories_l: Object.entries(categories),
    };
  },
};
</script>

<style lang="scss">
.app-road-archive {
  padding: 3rem 5rem;

  .roads-table {
    margin-top: 2rem;
  }

  .analytics {
    margin-top: 2rem;
  }
}
</style>
