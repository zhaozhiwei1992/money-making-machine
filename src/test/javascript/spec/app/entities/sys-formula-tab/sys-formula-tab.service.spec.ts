/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import SysFormulaTabService from '@/entities/sys-formula-tab/sys-formula-tab.service';
import { SysFormulaTab } from '@/shared/model/sys-formula-tab.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('SysFormulaTab Service', () => {
    let service: SysFormulaTabService;
    let elemDefault;

    beforeEach(() => {
      service = new SysFormulaTabService();
      elemDefault = new SysFormulaTab(123, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a SysFormulaTab', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a SysFormulaTab', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a SysFormulaTab', async () => {
        const returnedFromService = Object.assign(
          {
            tabEname: 'BBBBBB',
            colEname: 'BBBBBB',
            calFormula: 'BBBBBB',
            calFormulaDes: 'BBBBBB',
            roundType: 'BBBBBB',
            weight: 1,
            enable: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a SysFormulaTab', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a SysFormulaTab', async () => {
        const patchObject = Object.assign(
          {
            calFormulaDes: 'BBBBBB',
            roundType: 'BBBBBB',
            enable: true,
          },
          new SysFormulaTab()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a SysFormulaTab', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of SysFormulaTab', async () => {
        const returnedFromService = Object.assign(
          {
            tabEname: 'BBBBBB',
            colEname: 'BBBBBB',
            calFormula: 'BBBBBB',
            calFormulaDes: 'BBBBBB',
            roundType: 'BBBBBB',
            weight: 1,
            enable: true,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of SysFormulaTab', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a SysFormulaTab', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a SysFormulaTab', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
