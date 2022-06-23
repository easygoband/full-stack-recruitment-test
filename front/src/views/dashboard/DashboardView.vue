<script setup>
	import { ref, reactive, onMounted, onBeforeMount  } from 'vue';
	import { RouterLink, RouterView, useRouter } from 'vue-router'
	import { useI18n } from 'vue-i18n' 
	import { useForm, useField } from 'vee-validate';

	import { useUserStore } from '@/stores/userStore'
	import { GET_GENDER } from '@/utils/methods';

	import UserInventoryModal from './components/UserInventoryModal.vue'
	import UserReportModal from './components/ReportModal.vue'

	const userStore = useUserStore()
	const router = useRouter();
	const inventoryModal = ref(false);
	const reportModal = ref(false);

	const { locale, t: $t } = useI18n({})
	 
	onBeforeMount( () => {
        userStore.fnGetUserData();
	})
</script>



<template>
	<div class="container-fluid">
         <UserInventoryModal v-if="inventoryModal" @close=" inventoryModal = false " />
         <UserReportModal v-if="reportModal" @close=" reportModal = false " />
		<div class="row align-items-center justify-content-center zs-dashboard">
			<div class="col-11 ">
				<div class="card  animate__animated animate__fadeIn">
					<div class="card-header ">
                        <div class="row align-items-center">
                            <div class="col">
                                <h5 class="mb-1">
                                    <strong v-text="$t('general.name') + ': '"></strong>
                                    <span v-text=" userStore.userInfo.name + '   '"></span> 
                                </h5>
                                <p class="mb-1">
                                <small>
                                    <strong v-text="$t('general.age') + ': '"></strong>
                                    <span v-text="userStore.userInfo.age"></span>
                                </small> | 
                                <small>
                                    <strong v-text="$t('general.gender') + ': '"></strong>
                                    <span v-text=" $t('general.' + GET_GENDER(userStore.userInfo.gender)) "></span>
                                </small> | 
                                <small>
                                    <strong v-text="$t('general.points') + ': '"></strong>
                                    <span v-text=" userStore.userInfo.itemsPoints "></span>
                                </small>
                                </p>
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-outline-primary " @click="reportModal = true">
                                    <i class=" bi-card-checklist me-2"></i>
                                    <span v-text="$t('general.report')"></span>
                                </button>
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-primary " @click="inventoryModal = true">
                                    <i class=" bi-gear-wide-connected me-2"></i>
                                    <span v-text="$t('general.inventory')"></span>
                                </button>
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-sm btn-link " @click="userStore.fnLogout()" > 
                                    <small> 
                                        <span v-text="$t('general.logout')"></span>
                                    </small>
                                </button>
                            </div>
                        </div>
						
					</div>
					<div class="card-body py-4">
                        <RouterView />
					</div>
				</div>
			</div>
		</div>
	</div>

</template>
<style lang="scss">
	.zs-dashboard { 
		// background-color: #484E9B;
        height: 100vh;
		.card{
            height: 90vh; 
            box-shadow: 0px 5px 5px rgba($color: #000000, $alpha: 0.10);
		}
	}
</style>