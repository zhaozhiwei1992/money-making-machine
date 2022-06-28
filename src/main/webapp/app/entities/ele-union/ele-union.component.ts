import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IEleUnion } from '@/shared/model/ele-union.model';

import EleUnionService from './ele-union.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class EleUnion extends Vue {
  @Inject('eleUnionService') private eleUnionService: () => EleUnionService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public eleUnions: IEleUnion[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllEleUnions();
  }

  public clear(): void {
    this.retrieveAllEleUnions();
  }

  public retrieveAllEleUnions(): void {
    this.isFetching = true;
    this.eleUnionService()
      .retrieve()
      .then(
        res => {
          this.eleUnions = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IEleUnion): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeEleUnion(): void {
    this.eleUnionService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.eleUnion.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllEleUnions();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
