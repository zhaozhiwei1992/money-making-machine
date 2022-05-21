import { Authority } from '@/shared/security/authority';

const UiExample = () => import('@/example/uiexample.vue');
const HelloWorld = () => import('@/example/hello-world.vue');
const LeaveSlipAdd = () => import('@/example/leaveslip-add.vue');
const LeaveSlipAudit = () => import('@/example/leaveslip-audit.vue');

export default [
  // 这里的配置临时使用，优先级会高于数据库配置
  // {
  //   path: '/example/uiexample',
  //   name: 'DynamicUiExample',
  //   component: UiExample,
  //   meta: { authorities: [Authority.USER] },
  // },
  // {
  //   path: '/example/helloworld',
  //   name: 'HelloWorld',
  //   component: HelloWorld,
  //   meta: { authorities: [Authority.USER] },
  // },
  // // 请假录入
  // {
  //   path: '/example/leaveslip/add',
  //   name: 'LeaveSlipAdd',
  //   component: LeaveSlipAdd,
  //   meta: { authorities: [Authority.USER] },
  // },
  // {
  //   path: '/example/leaveslip/audit',
  //   name: 'LeaveSlipAudit',
  //   component: LeaveSlipAudit,
  //   meta: { authorities: [Authority.USER] },
  // },
];
