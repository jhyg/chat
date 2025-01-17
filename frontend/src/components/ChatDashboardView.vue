<template>

    <v-data-table
        :headers="headers"
        :items="chatDashboard"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ChatDashboardView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "roomId", value: "roomId" },
                { text: "participants", value: "participants" },
                { text: "lastMessageTime", value: "lastMessageTime" },
                { text: "content", value: "content" },
                { text: "timestamp", value: "timestamp" },
                { text: "senderId", value: "senderId" },
                { text: "messageId", value: "messageId" },
                { text: "roomName", value: "roomName" },
            ],
            chatDashboard : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/chatDashboards'))

            temp.data._embedded.chatDashboards.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.chatDashboard = temp.data._embedded.chatDashboards;
        },
        methods: {
        }
    }
</script>

