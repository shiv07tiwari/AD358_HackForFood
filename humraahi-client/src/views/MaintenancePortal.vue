<template>
  <div>
    <div v-show="!loaded">
      <div
        style="display: flex; width: 100%; justify-content: center; align-items: center; flex-direction: column; height: 70vh;"
      >
        <div class="spinner-border text-info mb-3" role="status">
          <span class="sr-only">Loading...</span>
        </div>
        <h3 style="font-weight: 300;">Loading please wait.</h3>
      </div>
    </div>
    <div v-show="loaded" class="app-maintenance-portal">
      <h1 style="font-weight: 300;">Maintenance Portal</h1>
      <div class="complaints-table">
        <h3 style="font-weight: 300; margin-bottom: 2rem;">All Active Complaints</h3>
        <div style="margin-bottom: 2rem;">
          <form class="form-inline">
            <label class="sr-only" for="inlineFormInputName2">Name</label>
            <input
              @change="(e) => {idChange(e)}"
              type="text"
              class="form-control mr-sm-2"
              id="inlineFormInputName2"
              placeholder="Search By Complaint ID"
            />
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
                v-for="district in districts_l"
                :key="district[0].toString()"
                :value="district[0]"
              >{{ district[1] }}</option>
            </select>
          </form>
        </div>
        <table class="table table-bordered header-fixed" style="border: 1px solid gray;">
          <thead class="thead-dark">
            <tr>
              <th scope="col">Complaint ID</th>
              <th scope="col">Road ID</th>
              <th scope="col">District</th>
              <th scope="col">Defect Type</th>
              <th scope="col">Reported On</th>
              <th scope="col">Remark</th>
              <th scope="col">Status</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="complaint in active" :key="complaint.complaint_id">
              <router-link
                tag="td"
                :to="{name: 'complaint', params: {id: complaint.complaint_id}}"
                style="cursor: pointer; text-decoration: underline;"
              >{{ complaint.complaint_id }}</router-link>
              <router-link
                tag="td"
                :to="{name: 'road', params: {id: complaint.road_id}}"
                style="cursor: pointer; text-decoration: underline;"
              >{{ complaint.road_id }}</router-link>
              <td>{{ districts[complaint.district] }}</td>
              <td>{{ complaint.defect_type }}</td>
              <td>{{ complaint.reported_month }}/{{ complaint.reported_year }}</td>
              <td>{{ complaint.remark }}</td>
              <td>{{ complaint.is_verified ? "Verified" : "Pending Verification" }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="analytics">
        <h3 style="font-weight: 300; margin-bottom: 3rem;">Analytics</h3>
        <div style="width: 500px;">
          <div style="display: flex;">
            <canvas id="piechart" ref="piechart"></canvas>
            <canvas id="barchart" ref="barchart" class="ml-5"></canvas>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { district } from "./enums";

import Chart from "chart.js";

export default {
  name: "maintenance-portal",
  components: {},
  methods: {
    defectsPie() {
      let counts = {
        pothole: 0,
        sign_board: 0,
        lights: 0,
        crack: 0,
        others: 0,
      };

      this.active.forEach((complaint) => {
        counts[complaint.defect_type] += 1;
      });

      new Chart(this.$refs.piechart, {
        type: "pie",
        data: {
          labels: ["pothole", "sign_board", "lights", "crack", "others"],
          datasets: [
            {
              label: "2018 Sales",
              data: [
                counts.pothole,
                counts.sign_board,
                counts.lights,
                counts.crack,
                counts.others,
              ],
              backgroundColor: [
                "#003f5c",
                "#58508d",
                "#ff6361",
                "#ffa600",
                "#ffc000",
              ],
            },
          ],
        },
        options: {
          title: {
            display: true,
            text: "Distribution by Defect Type",
            fontSize: 16,
          },
        },
      });
    },
    defectsByMonthBar() {
      let counts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

      this.active.forEach((complaint) => {
        counts[complaint.reported_month - 1] += 1;
      });

      console.log(counts);

      new Chart(this.$refs.barchart, {
        type: "bar",
        data: {
          labels: [
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
          ],
          datasets: [
            {
              data: counts,
              backgroundColor: [
                "#003f5c",
                "#58508d",
                "#ff6361",
                "#ffa600",
                "#ffc000",
                "#003f5c",
                "#58508d",
                "#ff6361",
                "#ffa600",
                "#ffc000",
                "#003f5c",
                "#58508d",
              ],
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
      if (e.target.value === "all" || e.target.value === "bvn") {
        this.active = this.complaints;
        this.redrawCharts();
        return;
      }

      this.active = this.complaints.filter((complaint) => {
        return complaint.district === e.target.value;
      });

      this.redrawCharts();
    },
    idChange(e) {
      console.log(e.target);

      if (e.target.value === "") {
        this.active = this.complaints;
        this.redrawCharts();
        return;
      }

      this.active = this.complaints.filter((complaint) => {
        return complaint.complaint_id == e.target.value;
      });
    },
    roadChange(e) {
      if (e.target.value === "") {
        this.active = this.complaints;
        this.redrawCharts();
        return;
      }

      this.active = this.complaints.filter((complaint) => {
        return complaint.road_id == e.target.value;
      });

      this.redrawCharts();
    },
    redrawCharts() {
      this.defectsPie();
      this.defectsByMonthBar();
    },
  },
  mounted() {
    this.axios
      .post("complaint", { op: "all_join_kp" })
      .then(({ data }) => {
        this.loaded = true;
        this.complaints = data.filter((complaint) => !complaint.is_resolved);
        this.active = this.complaints;

        setTimeout(() => {
          this.redrawCharts();
        }, 700);
      })
      .catch((err) => {
        console.error(err);
      });
  },
  data() {
    return {
      complaints: [],
      active: [],
      loaded: false,
      districts: district,
      districts_l: Object.entries(district),
    };
  },
};
</script>

<style lang="scss">
.app-maintenance-portal {
  padding: 3rem 5rem;

  .complaints-table {
    margin-top: 2rem;
  }

  .analytics {
    margin-top: 2rem;
  }
}
</style>
