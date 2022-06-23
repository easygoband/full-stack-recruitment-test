<script setup>
	import { ref, onMounted,  } from 'vue';
	import { useI18n } from 'vue-i18n' 
	import { useForm, useField } from 'vee-validate';

	import { useUserStore } from '@/stores/userStore'
	import { NewUserSchema } from '@/utils/schemas.js';

	import { CURRENT_LOCATION } from '@/utils/methods';


	const user = useUserStore()
	const submitBtn = ref(null)

	const { locale, t: $t } = useI18n({})

	const { errors, handleSubmit, isSubmitting, submitForm, setFieldValue } = useForm({ validationSchema: NewUserSchema, initialValues:{
		age: 18,
		gender: 'N',
		water: 0,
		food: 0,
		medication: 0,
		ammunition: 0,
	} });

	const { value: name } = useField('name');
	const { value: age } = useField('age');
	const { value: gender } = useField('gender');
	const { value: latitude } = useField('latitude');
	const { value: longitude } = useField('longitude');
	const { value: water } = useField('water');
	const { value: food } = useField('food');
	const { value: medication } = useField('medication');
	const { value: ammunition } = useField('ammunition');

	const fnOnSubmit = handleSubmit(values => {
		const payload = {
			name: values.name,
			age: values.age,
			gender: values.gender,
			location:{
			latitude: values.latitude,
			longitude: values.longitude,

			},
			items:{
				water: values.water,
				food: values.food,
				medication: values.medication,
				ammunition: values.ammunition,
			}
		}
		user.fnApiSignup(payload);
	});

	const fnGetCurrentLocation = () => {

		CURRENT_LOCATION().then( location => {
            setFieldValue('latitude', location.latitude );
			setFieldValue('longitude', location.longitude );
        });
	}

	onMounted( () => {
        fnGetCurrentLocation()
	})

    const emit = defineEmits(['back'])
</script>



<template>
    <div>
        <form @submit.prevent="fnOnSubmit" novalidate class=" ">
            <div class="row">
                <div class="col-12">
                    <div class="mb-4">
                        <label class="form-label" v-text="$t('general.name')"> </label>
                        <input type="text" class="form-control" v-model="name" :placeholder="$t('general.name')"
                            :class=" {'is-invalid' : errors.name } ">
                        <div class="invalid-feedback" v-text="errors.name"> </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="mb-4">
                        <label class="form-label" v-text="$t('general.age')"> </label>
                        <input type="number" class="form-control" v-model="age" :placeholder="$t('general.age')"
                            :class=" {'is-invalid' : errors.age } ">
                        <div class="invalid-feedback" v-text="errors.age"> </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="mb-4">
                        <label class="form-label" v-text="$t('general.gender')"> </label>
                        <select class="form-select" v-model="gender" :class=" {'is-invalid' : errors.gender } ">
                            <option value="M" v-text="$t('general.male')  "> </option>
                            <option value="F" v-text="$t('general.female')  "> </option>
                            <option value="N" v-text="$t('general.nonBinary')  "> </option>
                        </select>
                        <div class="invalid-feedback" v-text="errors.gender"> </div>
                    </div>
                </div>

                <div class="col-5">
                    <div class="">
                        <label class="form-label" v-text="$t('general.latitude')"> </label>
                        <input type="text" class="form-control" v-model="latitude" :placeholder="$t('general.latitude')"
                            :class=" {'is-invalid' : errors.latitude } ">
                        <div class="invalid-feedback" v-text="errors.latitude"> </div>
                    </div>
                </div>
                <div class="col-5">
                    <div class="">
                        <label class="form-label" v-text="$t('general.longitude')"> </label>
                        <input type="text" class="form-control" v-model="longitude"
                            :placeholder="$t('general.longitude')" :class=" {'is-invalid' : errors.longitude } ">
                        <div class="invalid-feedback" v-text="errors.longitude"> </div>
                    </div>
                </div>
                <div class="col-2 align-self-end">
                    <div class="">
                        <label class="form-label"></label>
                        <button type="button" @click="fnGetCurrentLocation" class="btn border w-100"> <i
                                class="bi bi-geo-alt-fill "></i> </button>
                    </div>
                </div>
                <div class="col-12">
                    <hr>
                    <h4 class="mb-4" v-text="$t('general.inventory')"></h4>
                </div>
                <div class="col-12 col-lg-6">
                    <div class="mb-4">
                        <label class="form-label" v-text="$t('general.water')"> </label>
                        <input type="number" class="form-control" v-model="water" :placeholder="$t('general.water')"
                            :class=" {'is-invalid' : errors.water } ">
                        <div class="invalid-feedback" v-text="errors.water"> </div>
                    </div>
                </div>
                <div class="col-12 col-lg-6">
                    <div class="mb-4">
                        <label class="form-label" v-text="$t('general.food')"> </label>
                        <input type="number" class="form-control" v-model="food" :placeholder="$t('general.food')"
                            :class=" {'is-invalid' : errors.food } ">
                        <div class="invalid-feedback" v-text="errors.food"> </div>
                    </div>
                </div>
                <div class="col-12 col-lg-6">
                    <div class="mb-4">
                        <label class="form-label" v-text="$t('general.medication')"> </label>
                        <input type="number" class="form-control" v-model="medication"
                            :placeholder="$t('general.medication')" :class=" {'is-invalid' : errors.medication } ">
                        <div class="invalid-feedback" v-text="errors.medication"> </div>
                    </div>
                </div>
                <div class="col-12 col-lg-6">
                    <div class="mb-4">
                        <label class="form-label" v-text="$t('general.ammunition')"> </label>
                        <input type="number" class="form-control" v-model="ammunition"
                            :placeholder="$t('general.ammunition')" :class=" {'is-invalid' : errors.ammunition } ">
                        <div class="invalid-feedback" v-text="errors.ammunition"> </div>
                    </div>
                </div>
                <input type="submit" hidden ref="submitBtn">
            </div>
        </form>
        <div class="row justify-content-end">
            <div class="col-auto">
                <button class="btn " v-text="$t('general.back')" @click=" emit('back') "></button>
            </div>
            <div class="col-auto">
                <button class="btn btn-primary " v-text="$t('general.signin')" @click=" submitBtn.click() "></button>
            </div>
        </div>

    </div>

</template>