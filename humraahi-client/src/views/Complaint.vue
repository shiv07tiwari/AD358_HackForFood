<template>
  <div class="app-complaint">
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
    <div v-show="loaded">
      <div class="header mt-4">
        <h1
          style="font-weight: 300; text-align:center;"
        >Complaint ID: {{complaint.complaint_id ? complaint.complaint_id : dummy.id}}</h1>
      </div>
      <div class="details mt-5" style="display: flex;">
        <div class="mapphotos" style="flex: 35% 0 0; margin-right: 2rem;">
          <div id="map" ref="map" style="width: 100%;"></div>
          <div class="images mt-4">
            <h5 style="font-weight: 300; margin-bottom: 1rem;">Images:</h5>
            <img src="@/assets/defect.jpg" style="width:80%;" />
          </div>
        </div>
        <div class="content" style="flex: 1">
          <button
            type="button"
            class="btn btn-primary"
            data-toggle="modal"
            data-target="#exampleModal"
          >View Inspection Report</button>

          <!-- Modal -->
          <div
            class="modal fade"
            id="exampleModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
          >
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Inspection Report</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body" style="padding: 2rem 4rem;">
                  <div class="row">
                    <div class="col col-md-4 mb-3" v-for="key in report_keys" :key="key">
                      <h5 style="text-transform: capitalize;">{{key.split('_').join(' ')}}</h5>
                      <p>{{ report[key] }}</p>
                    </div>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-success" data-dismiss="modal">Verify</button>
                  <button type="button" class="btn btn-danger">Reject</button>
                </div>
              </div>
            </div>
          </div>

          <div id="accordion" class="mt-4">
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
                    Location:
                    <span
                      class="ml-2"
                    >{{complaint.location ? complaint.location : dummy.location}}</span>
                  </div>
                  <div class="mt-2">
                    Type:
                    <span class="ml-2" style="text-transform: capitalize;">{{complaint.defect_type}}</span>
                  </div>
                  <div class="mt-2">
                    Reported On:
                    <span class="ml-2">23/04/2020</span>
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
                    <span
                      class="ml-2"
                    >{{complaint.cost ? complaint.cost : dummy.cost}} cr.</span>
                  </div>
                  <div class="mt-2">
                    Construction Completed:
                    <span
                      class="ml-2"
                    >{{complaint.completed ? complaint.completed : dummy.date}}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "complaint",
  components: {},
  data() {
    return {
      map: null,
      loaded: false,
      complaint: {},
      report: {
        sub_id: "1",
        road_name: "MG Road, Indore",
        inspection_codes: "Damage from cattle",
        road_grades: "Grade-A",
        radio_protocol_signs: "No",
        description: "Dummy",
        anticipated_turnout_constructed: "Dummy",
        overhead_power_lines: "Dummy",
        remedial_codes: "Dummy",
        priority: "Dummy",
        local_name: "Dummy",
        kilometre_marker_boards: "Dummy",
        hill: "Dummy",
        stop_sign: "Dummy",
        narrow_road_sections: "Dummy",
        audit: "Dummy",
        activities: "Dummy",
        rail_crossing_present: "Dummy",
        risk: "Dummy",
        stream_culvert: "Hanging Outlet",
        vehicle_usage: "Low",
      },
      report_keys: [],
      dummy: {
        id: this.$faker().random.uuid().substring(0, 5),
        location: this.$faker().address.city(),
      },
    };
  },
  mounted() {
    this.axios
      .post("complaint", {
        op: "byid_kp",
        args: parseInt(this.$route.params.id),
      })
      .then(({ data }) => {
        console.log(data);
        this.complaint = data[0];
        
        this.report_keys = Object.keys(this.report);

        console.log(this.$refs);

        this.map = new window.google.maps.Map(this.$refs["map"], {
          streetViewControl: false,
          center: { lat: this.complaint.lat, lng: this.complaint.lng },
          zoom: 18,
          width: 100,
        });

        new window.google.maps.Marker({
          position: { lat: this.complaint.lat, lng: this.complaint.lng },
          map: this.map,
          title: "Issue of " + " " + this.complaint.defect_type,
        });

        this.loaded = true;
      })
      .catch((err) => {
        console.error(err);
        this.map = new window.google.maps.Map(this.$refs["map"], {
          center: { lat: 23, lng: 72.5 },
          zoom: 18,
          width: 100,
        });

        new window.google.maps.Marker({
          position: { lat: 23, lng: 72.5 },
          map: this.map,
          title: "Pothole",
        });
      });
  },
};
</script>

<style lang="scss">
#map {
  height: 300px;
}

.app-complaint {
  padding: 2rem 5rem;

  .modal-dialog {
    min-width: 1200px;
  }

  .details {
    display: flex;

    .card {
      .card-header {
        background-color: #222;
        padding: 5px 1rem;
      }
    }
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
