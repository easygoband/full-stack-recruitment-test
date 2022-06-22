<script setup>
	import { ref, reactive,  } from 'vue';
	import { RouterLink, RouterView, useRouter } from 'vue-router'
	import { useI18n } from 'vue-i18n' 
	import { useForm, useField } from 'vee-validate';

	import { useUserStore } from '@/stores/userStore'
	import { LoginSchema } from '../utils/schemas.js';

	const user = useUserStore()
	const router = useRouter();

	const showModal = ref(false)

	const handleShowModal = () => {
		showModal.value = true
	}

	const handleCloseModal = () => {
		showModal.value = false
	}
	
	const {
		locale,
		t
	} = useI18n({})

	const {
		errors,
		handleSubmit,
		isSubmitting
	} = useForm({
		validationSchema: LoginSchema,
	});

	const {
		value: email
	} = useField('email');
	const {
		value: password
	} = useField('password');

	const fnOnSubmit = handleSubmit(values => {
		user.fnApiLogin( values.email , values.password);
	});
</script>



<template>
	<div class="container-fluid">
		<div class="row vh-100">
			<div class="col-6 red-salud bg-primary1  d-flex align-items-center justify-content-center h-100">
				<IconRedDeSalud />
			</div>
			<div class="col-6 d-flex flex-column justify-content-center align-items-center">
				<div class="row justify-content-center align-items-center w-100">
					<div class="col-10">
						<div class="text-left  align-self-start mb-4 ">
							<h3> <strong>Bienvenido a</strong> </h3>
							<h2> <strong>Red de salud</strong> </h2>
						</div>

						<h5 class="mb-4"> {{t('login.title')}}</h5>
						<form @submit.prevent="fnOnSubmit" novalidate class="w-100 needs-validation ">
							<div class="mb-5">
								<label for="email" class="form-label mb-4" v-text="t('login.email')"> </label>
								<input type="email" class="form-control" name="email" id="email" v-model="email"
									placeholder="Introducir email" :class=" {'is-invalid' : errors.email } ">
								<div class="invalid-feedback" v-text="errors.email"> </div>
							</div>
							<div class="mb-5">
								<label for="password" class="form-label mb-4">{{t('general.password')}}</label>
								<input type="password" class="form-control" name="password" id="password" v-model="password"
									placeholder="Introducir password" :class=" {'is-invalid' : errors.password } ">
								<div class="invalid-feedback" v-text="errors.password"> </div>
							</div>

							<button class="btn btn-lg btn-rounded btn-primary mb-4 w-100 text-strong"
								v-text="'Iniciar sesión'"></button>

							<RouterLink class="btn btn-lg btn-rounded w-100 bg-support-info-20 text-primary text-strong mb-4" to="/dashboard"
								v-text="'Registarse'" />

							<div class="text-center">
                				<a class="input-label color-brand-02" href="javascript:;" @click="handleShowModal">{{t('recovery.link')}}</a>
            				</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</template>
<style lang="scss">
	.red-salud {
		background-image: url('@/assets/images/heart.svg');
		background-color: #484E9B;
		background-repeat: no-repeat;
		background-position-y: 50%;
		background-position-x: 0px;
	}

	.modal-title {
		font-weight: 500;
		line-height: 24px;
		color: #232735;
	}

	.mt-s {
		margin-top: 1em;
	}

	.forgot-icon{
		margin-top: 3em;
		padding-left: 3.5em;
}
</style>