<script setup>
	import { ref, reactive, onMounted,  } from 'vue';
	import { RouterLink, RouterView, useRouter } from 'vue-router'
	import { useI18n } from 'vue-i18n' 
	import { useForm, useField } from 'vee-validate';
	import { GET_GENDER } from '@/utils/methods';

	import { useUserStore } from '@/stores/userStore'


	const userStore = useUserStore()
	const router = useRouter();

	const { locale, t: $t } = useI18n({})
	 
	onMounted( () => {
		userStore.fnApiGetUserList(true);
	})
</script>



<template>
	<div class=" p-5 ">
		<table class="table ">
			<thead>
				<tr>
					<th v-text="$t('general.name')"></th>
					<th v-text="$t('general.age')"></th>
					<th v-text="$t('general.gender')"></th>
					<th class="text-center" v-text="$t('general.status')"></th>
				</tr>
			</thead>
			<tbody v-if="!userStore.loading.data">
				<tr v-for=" (user,index ) in userStore.userList" :key="index">
					<td >
						<router-link :to="'/dashboard/users/' + user._id" v-text="user.name">

						</router-link>
					</td>
					<td v-text="user.age"></td>
					<td v-text="$t('general.' + GET_GENDER(user.gender) ) "></td>
					<td class="text-center">
						<span class="badge" :class="user.infected ? 'bg-danger' : 'bg-success' "
							v-text="user.infected ? $t('general.infected') : $t('general.healthy')"></span>
					</td>
				</tr>
			</tbody>
			<tbody v-else>
				<tr>
					<td colspan="4" class="text-center">
						<div class="spinner-border m-5" role="status">
							<span class="visually-hidden"></span>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

</template>
<style lang="scss">
</style>