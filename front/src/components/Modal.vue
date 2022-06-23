<template>
	<transition name="modal-fade">
		<div class="modal d-block" tabindex="-1">
			<div class="modal-dialog modal-dialog-centered" :class=" props.centered ? 'modal-dialog-centered' : '' " >
				<div class="modal-content">
					<header class="modal-header">
						<slot name="header"> This is the default title! </slot>
						<button type="button" class="btn-close" @click="emit('close')"></button>
					</header>

					<section class="modal-body">
						<slot name="body"> This is the default body! </slot>
					</section>

					<section class="modal-footer">
						<slot name="footer"> This is the default footer! </slot>
					</section>
				</div>
			</div>
		</div>
	</transition>
</template>

<script setup>
	const props = defineProps({
        centered: {
            type: Boolean,
            default: false,
        },
    })
    const emit = defineEmits(['close'])
</script>


<style lang="scss">
	.modal-dialog{
	min-width: 800px;	
	}
	.modal-content {
		box-shadow: 0 20px 60px -2px rgb(27 33 58 / 40%);
		border-radius: 0.5em;
		border-width: 0px;
	}

	.modal-header,
	.modal-footer {
		padding: 1.4rem 1.5rem;
		display: flex;
	}

	.modal-header {
		position: relative;
		border-bottom: 1px solid #eeeeee;
		justify-content: space-between;
	}

	.modal-body {
		position: relative;
		padding: 1em 1.5em;
	}

	.modal-fade-enter,
	.modal-fade-leave-to {
		opacity: 0;
	}

	.modal-fade-enter-active,
	.modal-fade-leave-active {
		transition: opacity .5s ease;
	}

	.modal {
		background-color: rgba($color: #000000, $alpha: 0.3);
	}
</style>