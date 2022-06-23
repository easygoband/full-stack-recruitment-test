<script setup>
	import { ref } from "vue";
	import Modal from "@/components/Modal.vue";
	import { useI18n } from "vue-i18n";
	import { CURRENT_LOCATION } from '@/utils/methods';

	const { locale, t: $t } = useI18n({});
    const props = defineProps( {
        survivorId:{
            type: String,
            required: true,
        }
    })
	const emit = defineEmits(['close','accept']);

    import { useUserStore } from '@/stores/userStore'
	const userStore = useUserStore()

    const fnUserInfected = async () => {
        await userStore.fnApiUserInfected( props.survivorId);
        emit('close')
    }
</script>

<template>
	<Modal centered @close="emit('close')" >
		<template v-slot:header>
			<h4 class="modal-title" v-text="$t('general.confirmation')"></h4>
		</template>
		<template v-slot:body>
			<div class="container py-5">
                <h5 class="text-center" v-text="$t('general.userInfectedMessage')">
                </h5>
			</div>
		</template>
		<template v-slot:footer>
		    <div class="row">
		        <div class="col-auto">
		            <button class="btn btn-light " @click="emit('close')" v-text="$t('general.close')"> </button>
		        </div>
		        <div class="col-auto">
		            <button class="btn btn-danger " @click="fnUserInfected()">
		                <div v-if="userStore.loading.infected ">
		                    <span class="spinner-border spinner-border-sm"></span>
		                </div>
		                <div v-else>
		                    <i class="bi-radioactive me-2"></i>
		                    <span v-text="$t('general.continue')"></span>
		                </div>
		            </button>
		        </div>
		    </div>
		</template>
	</Modal>
</template>