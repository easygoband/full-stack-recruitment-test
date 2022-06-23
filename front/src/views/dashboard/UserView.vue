<script setup>
	import { ref, reactive, onMounted,  } from 'vue';
	import { RouterLink, RouterView, useRouter, useRoute } from 'vue-router'
	import { useI18n } from 'vue-i18n' 
	import { GET_GENDER } from '@/utils/methods';
	import UserInfectedModal from './components/UserInfectedModal.vue'

	import { useUserStore } from '@/stores/userStore'
	const userStore = useUserStore()
	const route = useRoute();

	const { locale, t: $t } = useI18n({})
	const infectedModal = ref(false);

	onMounted( () => {
		userStore.fnApiGetUserInfo(route.params.userId);
	})
</script>



<template>
	<div class=" p-5 ">
		<UserInfectedModal :survivorId="route.params.userId" v-if="infectedModal" @close=" infectedModal = false " />
		<div v-if="!userStore.loading.survivorInfo">
			<div class="row mb-4">
				<div class="col-12 border-bottom pb-4 border-primary">
					<div class="row align-items-center ">
						<div class="col-1" v-if="userStore.survivorInfo.infected">
							<img src="@/assets/zombie.png" class="img-fluid" alt="">
						</div>
						<div class="col">
							<div class="row align-items-center ">
								<div class="col-auto">
									<h2 class="mb-0">
										<strong v-text="userStore.survivorInfo.name"></strong>
									</h2>
								</div>
								<div class="col-auto ps-0">
									<span class="badge "
										:class="userStore.survivorInfo.infected ? 'bg-danger' : 'bg-success' "
										v-text="userStore.survivorInfo.infected ? $t('general.infected') : $t('general.healthy')"></span>
								</div>
								<div class="col-12">
									<p class="m-0">
										<small>
											<strong v-text="$t('general.age') + ': '"></strong>
											<span v-text="userStore.survivorInfo.age"></span>
										</small> |
										<small>
											<strong v-text="$t('general.gender') + ': '"></strong>
											<span
												v-text=" $t('general.' + GET_GENDER(userStore.survivorInfo.gender)) "></span>
										</small> | 
										<small>
											<strong v-text="$t('general.points') + ': '"></strong>
											<span v-text=" userStore.survivorInfo.itemsPoints "></span>
										</small>
									</p>
								</div>
							</div>
						</div>
						<div class="col-auto">
							<div class="row justify-content-between ">
								<div class="col-auto ">
									<router-link to="/dashboard" class="btn btn-outline-primary w-100">
										<i class="bi-arrow-left-square-fill me-2"></i>
										<span v-text="$t('general.return')"></span>
									</router-link>
								</div>
								<div class="col-auto" v-if="!userStore.survivorInfo.infected">
									<button class="btn btn-danger w-100" @click="infectedModal= true">
										<i class="bi-radioactive me-2"></i>
										<span v-text="$t('general.reportContagion')"></span>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-5 pe-4">
					<h4 v-text=" $t('general.inventory')" class="mb-4"></h4>
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
								<td v-text="userStore.survivorInfo.items.water"></td>
							</tr>
							<tr>
								<td v-text=" $t('general.food')"></td>
								<td v-text="userStore.survivorInfo.items.food"></td>
							</tr>
							<tr>
								<td v-text=" $t('general.medication')"></td>
								<td v-text="userStore.survivorInfo.items.medication"></td>
							</tr>
							<tr>
								<td v-text=" $t('general.ammunition')"></td>
								<td v-text="userStore.survivorInfo.items.ammunition"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-7 border-start ps-4">
					<h6 class="mb-1">
						<strong v-text="$t('general.lastLocation')"></strong>
					</h6>
					<p class="m-0">
						<small>

							<strong v-text="$t('general.latitude') + ': '"></strong>
							<span v-text=" userStore.survivorInfo.location.latitude "></span>
							<span v-text=" ' | ' "></span>
							<strong v-text="$t('general.longitude') + ': '"></strong>
							<span v-text=" userStore.survivorInfo.location.longitude "></span>
						</small>
					</p>
				</div>
			</div>




		</div>
		<div class="row justify-content-center " v-else>
			<div class="col-auto">
				<div class=" spinner-border m-2" role="status">
					<span class="visually-hidden"></span>
				</div>
			</div>
		</div>
	</div>

</template>
<style lang="scss">
</style>