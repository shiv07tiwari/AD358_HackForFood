<template>
  <div class="app-road">
    <template v-if="!loaded">
      <div
        style="display: flex; width: 100%; justify-content: center; align-items: center; flex-direction: column; height: 70vh;"
      >
        <div class="spinner-border text-info mb-3" role="status">
          <span class="sr-only">Loading...</span>
        </div>
        <h3 style="font-weight: 300;">Loading please wait.</h3>
      </div>
    </template>
    <template v-else>
      <div class="header">
        <h1
          style="font-weight: 300; text-align:center;"
        >Road ID: {{road.road_id ? road.road_id : dummy.id}}</h1>
        <h3 style="font-weight: 300; text-align:center;">{{road.name ? road.name : dummy.name}}</h3>
      </div>
      <div class="details">
        <div class="mapphotos" style="flex: 35% 0 0">
          <div class="map">
            <iframe :src="road.mapUrl ? road.mapUrl : dummy.mapUrl" width="550" height="360"></iframe>
          </div>
          <div class="images mt-4">
            <h5 style="font-weight: 300; margin-bottom: 1rem;">Images:</h5>
            <img src="@/assets/ne1/p2.jpg" width="550" />
          </div>
        </div>
        <div class="content" style="flex: 1">
          <div id="accordion">
            <div class="card">
              <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                  <button
                    class="btn btn-link accordian-btn"
                    data-toggle="collapse"
                    data-target="#collapseOne"
                    aria-expanded="true"
                    aria-controls="collapseOne"
                  >Location Details</button>
                </h5>
              </div>

              <div
                id="collapseOne"
                class="collapse show"
                aria-labelledby="headingOne"
                data-parent="#accordion"
              >
                <div class="card-body" style="padding: 2rem; font-size: 1.3rem;">
                  <div class="mt-2">
                    Start:
                    <span class="ml-2">{{road.start ? road.start : dummy.start}}</span>
                  </div>
                  <div class="mt-2">
                    End:
                    <span class="ml-2">{{road.end ? road.end : dummy.end}}</span>
                  </div>
                  <div class="mt-2">
                    Exit Loops:
                    <span class="ml-2">{{road.exitPoints__001}}</span>,
                    <span class="ml-1">{{road.exitPoints__002}}</span>,
                    <span class="ml-1">{{road.exitPoints__003}}</span>,
                    <span class="ml-1">{{road.exitPoints__004}}</span>
                  </div>
                  <div class="mt-2">
                    Length:
                    <span class="ml-2">{{road.length ? road.length : dummy.length}}km</span>
                  </div>
                  <div class="mt-2">
                    Category:
                    <span
                      class="ml-2"
                    >{{road.category ? categories[road.category] : dummy.category}}</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="card-header" id="headingFour">
                <h5 class="mb-0">
                  <button
                    class="btn btn-link accordian-btn"
                    data-toggle="collapse"
                    data-target="#collapseFour"
                    aria-expanded="true"
                    aria-controls="collapseFour"
                  >Construction Details</button>
                </h5>
              </div>

              <div
                id="collapseFour"
                class="collapse"
                aria-labelledby="headingFour"
                data-parent="#accordion"
              >
                <div class="card-body" style="padding: 2rem; font-size: 1.3rem;">
                  <div>
                    Construction Cost:
                    <span class="ml-2">{{road.cost ? road.cost : dummy.cost}} cr.</span>
                  </div>
                  <div class="mt-2">
                    Construction Completed:
                    <span
                      class="ml-2"
                    >{{road.completed ? road.completed : dummy.date}}</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="card">
              <div class="card-header" id="headingTwo">
                <h5 class="mb-0">
                  <button
                    class="btn btn-link collapsed accordian-btn"
                    data-toggle="collapse"
                    data-target="#collapseTwo"
                    aria-expanded="false"
                    aria-controls="collapseTwo"
                  >Technical Details</button>
                </h5>
              </div>
              <div
                id="collapseTwo"
                class="collapse"
                aria-labelledby="headingTwo"
                data-parent="#accordion"
              >
                <div
                  class="card-body"
                >Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
              </div>
            </div>
            <div class="card">
              <div class="card-header" id="headingThree">
                <h5 class="mb-0">
                  <button
                    class="btn btn-link collapsed accordian-btn"
                    data-toggle="collapse"
                    data-target="#collapseThree"
                    aria-expanded="false"
                    aria-controls="collapseThree"
                  >Road Segments</button>
                </h5>
              </div>
              <div
                id="collapseThree"
                class="collapse"
                aria-labelledby="headingThree"
                data-parent="#accordion"
              >
                <div
                  class="card-body"
                >Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="prediction">
        <h3
          style="font-weight: 300; margin-bottom: 2rem; margin-top:3rem;"
        >Predicted upcoming repair date</h3>
        <div style="width: 40%;">
          <div class="alert alert-success" role="alert">
            Upcoming Repair Date: {{ upcoming }} Months
          </div>
        </div>
      </div>
      <div class="complaints">
        <h3 style="font-weight: 300; margin-bottom: 2rem;">Active Complaints</h3>
        <table class="table table-bordered" style="border: 1px solid gray;">
          <thead class="thead-dark">
            <tr>
              <th scope="col">Complaint ID</th>
              <th scope="col">Defect Type</th>
              <th scope="col">Reported On</th>
              <th scope="col">Remark</th>
              <th scope="col">Status</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="complaint in complaints" :key="complaint.id">
              <router-link
                tag="td"
                :to="{name: 'complaint', params: {id: complaint.complaint_id}}"
                style="cursor: pointer; text-decoration: underline;"
              >{{ complaint.complaint_id }}</router-link>
              <td>{{ complaint.defect_type }}</td>
              <td>{{ complaint.reported_month }}/{{ complaint.reported_year }}</td>
              <td>{{ complaint.remark }}</td>
              <td>{{ complaint.is_verified ? "Verified" : "Pending Verification" }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="tenders mt-4">
        <h3 style="font-weight: 300; margin-bottom: 2rem;">Previous Repairs and Tenders</h3>
        <table class="table table-bordered" style="border: 1px solid gray;">
          <thead class="thead-dark">
            <tr>
              <th scope="col">Tender ID</th>
              <th scope="col">Expected Cost</th>
              <th scope="col">Actual Cost</th>
              <th scope="col">Started On</th>
              <th scope="col">Completed On</th>
              <th scope="col">Remarks</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="tender in tenders" :key="tender.id">
              <td>{{tender.tender_id}}</td>
              <td>₹{{tender.est_cost}}</td>
              <td>
                ₹{{tender.act_cost}}
                <span
                  class="text-danger ml-3"
                  v-if="tender.act_cost > tender.est_cost"
                >+{{tender.act_cost - tender.est_cost}}</span>
              </td>
              <td>{{tender.cons_month}}/{{tender.cons_year}}</td>
              <td>{{tender.comp_month + 1}}/{{tender.comp_year}}</td>
              <td>{{tender.tender_remarks}}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </template>
  </div>
</template>

<script>
// import roads from "./roads";
import { categories } from "./enums";

export default {
  name: "road",
  components: {},
  computed: {},
  data() {
    return {
      loaded: false,
      roadid: "",
      categories: categories,
      road: {},
      complaints: [],
      upcoming: 0,
      tenders: [],
      dummy: {
        mapUrl:
          "https://www.google.com/maps/d/u/0/embed?mid=12cBAhgQUhwfqIuE3mBGX-7mMcpWz8UFt",
        id: this.$faker().random.uuid().substring(0, 5),
        name: this.$faker().address.streetName(),
        category: "State Highway",
        cost: Math.round(Math.random() * 100),
        date: this.$faker().date.past(),
        start: this.$faker().address.city(),
        end: this.$faker().address.city(),
        length: Math.round(Math.random() * 100),
        complaints: [],
        tenders: [],
      },
    };
  },
  mounted() {
    this.axios
      .post("road", { op: "byid_kp", args: parseInt(this.$route.params.id) })
      .then(({ data }) => {
        console.log(data);
        this.road = data[0];
        this.loaded = true;
        console.log(this.road);

        this.axios
          .post("tender", {
            op: "roads_kp",
            args: parseInt(this.$route.params.id),
          })
          .then(({ data }) => {
            this.tenders = data;
          });

        this.axios.get("cost_pred").then(({data}) => {
          this.upcoming = data.time[parseInt(this.$route.params.id)];
        }).catch(err => {
          console.error(err);
        })

        this.axios
          .post("complaint", {
            op: "roads_kp",
            args: parseInt(this.$route.params.id),
          })
          .then(({ data }) => {
            this.complaints = data;
          });
      })
      .catch((err) => {
        console.error(err);
      });

    for (let i = 0; i < 3 + Math.ceil(Math.random() * 10); i++) {
      this.dummy.tenders.push({
        id: this.$faker().random.uuid().substring(0, 5),
        expectedCost: Math.ceil(Math.random() * 100000),
        actualCost: Math.ceil(Math.random() * 100000),
        startedOn: this.$faker().date.past(),
        completedOn: this.$faker().date.past(),
        remarks: this.$faker().lorem.sentence(),
        task: this.$faker().lorem.sentence(),
      });
    }

    for (let i = 0; i < 3 + Math.ceil(Math.random() * 5); i++) {
      this.dummy.complaints.push({
        id: this.$faker().random.uuid().substring(0, 5),
        roadid: this.$faker().random.uuid().substring(0, 5),
        location: this.$faker().address.streetAddress(),
        district: this.$faker().address.city(),
        type: "Pothole",
        reported: this.$faker().date.past(),
        status: "Not Verified",
      });
    }
  },
};
</script>

<style lang="scss">
.app-road {
  padding: 2rem 5rem;

  .details {
    display: flex;
    margin-top: 4rem;

    .card {
      .card-header {
        background-color: #222;
        padding: 5px 1rem;
      }
    }
  }

  .complaints {
    margin-top: 4rem;
  }

  .accordian-btn {
    color: white;

    &:active,
    &:focus {
      outline: none;
      box-shadow: none;
    }
  }
}
</style>
