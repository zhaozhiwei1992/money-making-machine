import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IMenu, Menu } from '@/shared/model/menu.model';
import MenuService from './menu.service';

const validations: any = {
  menu: {
    url: {},
    name: {},
    iconCls: {},
    ordernum: {},
    keepAlive: {},
    requireAuth: {},
    parentId: {},
    enabled: {},
    config: {},
  },
};

@Component({
  validations,
})
export default class MenuUpdate extends Vue {
  @Inject('menuService') private menuService: () => MenuService;
  @Inject('alertService') private alertService: () => AlertService;

  public menu: IMenu = new Menu();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.menuId) {
        vm.retrieveMenu(to.params.menuId);
      } else {
        vm.menu.config = '{}';
        vm.menu.parentId = 0;
        vm.menu.enabled = true;
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
    if (this.menu.id) {
      this.menuService()
        .update(this.menu)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.menu.updated', { param: param.id });
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
      this.menuService()
        .create(this.menu)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.menu.created', { param: param.id });
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

  public retrieveMenu(menuId): void {
    this.menuService()
      .find(menuId)
      .then(res => {
        this.menu = res;
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
