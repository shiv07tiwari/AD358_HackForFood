<template>
  <div class="app-complaint">
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
    <template v-show="loaded">
      <div class="header mt-4">
        <h1
          style="font-weight: 300; text-align:center;"
        >Complaint ID: {{complaint.id ? complaint.id : dummy.id}}</h1>
      </div>
      <div class="details mt-5" style="display: flex;">
        <div class="mapphotos" style="flex: 35% 0 0; margin-right: 2rem;">
          <div id="map" ref="map" style="width: 100%;"></div>
          <div class="images mt-4">
            <h5 style="font-weight: 300; margin-bottom: 1rem;">Images:</h5>
            <img src="@/assets/defect.jpg" style="width:100%;" />
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
                    Location:
                    <span
                      class="ml-2"
                    >{{complaint.location ? complaint.location : dummy.location}}</span>
                  </div>
                  <div class="mt-2">
                    Type:
                    <span class="ml-2">Pothole</span>
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
    </template>
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
      dummy: {
        id: this.$faker().random.uuid().substring(0, 5),
        location: this.$faker().address.city(),
      },
    };
  },
  mounted() {
    setTimeout(() => {
      this.loaded = true;

      console.log(this.$refs);

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

    }, 300);
  },
};
</script>

<style lang="scss">
#map {
  height: 300px;
}

.app-complaint {
  padding: 2rem 5rem;

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
