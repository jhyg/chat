<template>

    <v-card outlined>
        <v-card-title>
            ChatDashboard # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="RoomId" v-model="item.roomId" :editMode="editMode" @change="change" />
            </div>
            <StringList offline label="Participants" v-model="item.participants" :editMode="false" @change="change" />
            <div>
                <Date label="LastMessageTime" v-model="item.lastMessageTime" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Content" v-model="item.content" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Timestamp" v-model="item.timestamp" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="SenderId" v-model="item.senderId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="MessageId" v-model="item.messageId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="RoomName" v-model="item.roomName" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ChatDashboardViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/chatDashboards/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

