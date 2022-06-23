<script setup>
	import { ref } from "vue";
	import Modal from "@/components/Modal.vue";
	import { useI18n } from "vue-i18n";
	import { CURRENT_LOCATION } from '@/utils/methods';

	const { locale, t: $t } = useI18n({});

	const emit = defineEmits(['close','accept']);

    import { useUserStore } from '@/stores/userStore'
	const userStore = useUserStore()

    const fnUpdateLocation = async () => {
        userStore.loading.location = true;
        await CURRENT_LOCATION().then( location => {
            const payload = {
                latitude: location.latitude,
                longitude: location.longitude,
            }
            userStore.fnApiUpdateLocation( payload);
        });
    }
</script>

<template>
	<Modal centered @close="emit('close')" >
		<template v-slot:header>
			<h4 class="modal-title" v-text="$t('general.inventory')"></h4>
		</template>
		<template v-slot:body>
			<div class="container">
                
                <table class="table">
                    <thead>
                        <tr>
                            <th v-text=" $t('general.item') "> </th>
                            <th v-text=" $t('general.quantity') "> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td v-text=" $t('general.water')"></td>
                            <td v-text="userStore.userInfo.items.water"></td>
                        </tr>
                        <tr>
                            <td v-text=" $t('general.food')"></td>
                            <td v-text="userStore.userInfo.items.food"></td>
                        </tr>
                        <tr>
                            <td v-text=" $t('general.medication')"></td>
                            <td v-text="userStore.userInfo.items.medication"></td>
                        </tr>
                        <tr>
                            <td v-text=" $t('general.ammunition')"></td>
                            <td v-text="userStore.userInfo.items.ammunition"></td>
                        </tr>
                    </tbody>
                </table>
                <hr class="my-4">
                <div class="mb-4">
                    <div class="row align-items-center ">
                        <div class="col">
                            <h6 class="mb-1">
                                <strong v-text="$t('general.lastLocation')"></strong>
                            </h6>
                            <p class="m-0">
                                <small>

                                    <strong v-text="$t('general.latitude') + ': '"></strong>
                                    <span v-text=" userStore.userInfo.location.latitude "></span>
                                    <span v-text=" ' | ' "></span>
                                    <strong v-text="$t('general.longitude') + ': '"></strong>
                                    <span v-text=" userStore.userInfo.location.longitude "></span>
                                </small>
                            </p>
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-sm btn-primary" :disabled="userStore.loading.location"
                                @click="fnUpdateLocation()">
                                <div v-if="userStore.loading.location ">
                                    <span class="spinner-border spinner-border-sm"></span>
                                </div>
                                <div v-else>
                                    <i class="bi-geo-alt-fill me-2"></i>
                                    <span v-text="$t('general.updateLocation')"></span>
                                </div>
                            </button>
                        </div>
                    </div>

                </div>
			</div>
		</template>
		<template v-slot:footer>
			<div class="row">
				<div class="col-auto">
					<button class="btn btn-light " @click="emit('close')" v-text="$t('general.close')"> </button>
				</div>
            </div>
		</template>
	</Modal>
</template>