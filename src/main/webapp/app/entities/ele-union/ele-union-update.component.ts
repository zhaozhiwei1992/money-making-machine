import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IEleUnion, EleUnion } from '@/shared/model/ele-union.model';
import EleUnionService from './ele-union.service';

const validations: any = {
  eleUnion: {
    eleCatCode: {},
    eleCatName: {},
    eleCode: {},
    eleName: {},
    parentId: {},
    levelNo: {},
    isLeaf: {},
    isEnabled: {},
    createTime: {},
    updateTime: {},
  },
};

@Component({
  validations,
})
export default class EleUnionUpdate extends Vue {
  @Inject('eleUnionService') private eleUnionService: () => EleUnionService;
  @Inject('alertService') private alertService: () => AlertService;

  public eleUnion: IEleUnion = new EleUnion();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eleUnionId) {
        vm.retrieveEleUnion(to.params.eleUnionId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.eleUnion.id) {
      this.eleUnionService()
        .update(this.eleUnion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.eleUnion.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.eleUnionService()
        .create(this.eleUnion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.eleUnion.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveEleUnion(eleUnionId): void {
    this.eleUnionService()
      .find(eleUnionId)
      .then(res => {
        this.eleUnion = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
