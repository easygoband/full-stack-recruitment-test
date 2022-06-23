<script setup>
	import { ref, onMounted } from "vue";
	import Modal from "@/components/Modal.vue";
	import { useI18n } from "vue-i18n";

	const { locale, t: $t } = useI18n({});

	const emit = defineEmits(['close']);

    import { useUserStore } from '@/stores/userStore'
	const userStore = useUserStore()


	onMounted( ()=>{
		userStore.fnApiGetReport();
	})
</script>

<template>
	<Modal centered @close="emit('close')" >
		<template v-slot:header>
			<h4 class="modal-title" v-text="$t('general.report')"></h4>
		</template>
		<template v-slot:body>
			<div class="container py-5">
				<div class="row">
					<div class="col-4 text-center ">
						<h3 v-text="userStore.report.users.total"> </h3>
						<h6 v-text="$t('general.notinfected')"></h6>
					</div>
					<div class="col-4 text-center ">
						<h3 v-text="userStore.report.infected.total"> </h3>
						<h6 v-text="$t('general.sinfected')"></h6>
					</div>
					<div class="col-4 text-center ">
						<h3 v-text="userStore.report.infected.points"> </h3>
						<h6 v-text="$t('general.lostPoints')"></h6>
					</div>
				</div>
				<hr class="my-5">
				<h4 class=" text-center mb-5" v-text=" $t('general.average') "> </h4>
				<div class="row">
					<div class="col-3 mb-4 text-center ">
						<h3 v-text=" Math.floor( userStore.report.users.water / userStore.report.users.total ) "> </h3>
						<h6 v-text="$t('general.water')"></h6>
					</div>
					<div class="col-3 mb-4 text-center ">
						<h3 v-text="Math.floor( userStore.report.users.food / userStore.report.users.total)"> </h3>
						<h6 v-text="$t('general.food')"></h6>
					</div>
					<div class="col-3 mb-4 text-center ">
						<h3 v-text="Math.floor( userStore.report.users.medication / userStore.report.users.total)"> </h3>
						<h6 v-text="$t('general.medication')"></h6>
					</div>
					<div class="col-3 mb-4 text-center ">
						<h3 v-text="Math.floor( userStore.report.users.ammunition / userStore.report.users.total)"> </h3>
						<h6 v-text="$t('general.ammunition')"></h6>
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