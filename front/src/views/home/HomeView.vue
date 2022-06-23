<script setup>
	import { ref, reactive, onMounted,  } from 'vue';
	import { RouterLink, RouterView, useRouter } from 'vue-router'
	import { useI18n } from 'vue-i18n' 
	import { useForm, useField } from 'vee-validate';

	import { useUserStore } from '@/stores/userStore'
	import { NewUserSchema } from '../../utils/schemas.js';

	import SignupForm from './components/SignupForm.vue'  

	const userStore = useUserStore()
	const router = useRouter();
	const signupForm = ref(false)
	const submitBtn = ref(null)

	const { locale, t: $t } = useI18n({})
	 
	onMounted( () => {
		userStore.fnApiGetUserList();
	})
</script>



<template>
	<div class="container-fluid">
		<div class="row align-items-center justify-content-center vh-100 zs-login">
			<div class="col-11  col-lg-6 col-xl-5">
				<div class="card  animate__animated animate__fadeIn">
					<div class="card-header">
						<h1 class="text-center" v-text="$t('general.survive')"></h1>
					</div>
					<div class="card-body py-4">
						<div class=" d-flex flex-column justify-content-center align-items-center animate__animated animate__fadeIn" v-if="!signupForm">
							<div class="w-100" v-if="!userStore.loading.data">
								<div class="row align-items-center" v-for=" (user,index ) in userStore.userList" :key="index">
									<div class="col-4">
										<p class="m-0">
											<span  v-text="user.name"></span> <br>
											<small v-if="user.infected">
												<span class="badge bg-danger" v-text="$t('general.infected')"></span>
											</small>
										</p>
									</div>
									<div class="col-auto ms-auto">
										<button class="btn btn-outline-primary btn-sm" :disabled="user.infected" v-text="$t('general.enter')" @click="userStore.fnApiLogin(user.name)">
										</button>
									</div>
									<div class="col-12">
										<hr class="my-4">
									</div>
								</div>
							</div>

							<div class="spinner-border m-5" role="status" v-else>
								<span class="visually-hidden"></span>
							</div>
							
						</div>
						<SignupForm class="animate__animated animate__fadeIn" @back="signupForm = false" v-else />
					</div>
					<div class="card-footer d-flex justify-content-end animate__animated animate__fadeIn" v-if="!signupForm">
						<button class="btn btn-link " v-text="$t('general.signup')" @click="signupForm = true" v-if="!signupForm"></button>
					</div>
				</div>
			</div>
		</div>
	</div>

</template>
<style lang="scss">
	.zs-login { 
		background-color: #484E9B;
		.card{
			min-height: 450px;
            box-shadow: 0px 10px 10px rgba($color: #000000, $alpha: 0.20);
		}
	}
</style>