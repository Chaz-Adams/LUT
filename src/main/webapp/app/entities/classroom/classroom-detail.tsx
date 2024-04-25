import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './classroom.reducer';

export const ClassroomDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const classroomEntity = useAppSelector(state => state.classroom.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="classroomDetailsHeading">Classroom</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{classroomEntity.id}</dd>
          <dt>
            <span id="classroomId">Classroom Id</span>
          </dt>
          <dd>{classroomEntity.classroomId}</dd>
          <dt>
            <span id="className">Class Name</span>
          </dt>
          <dd>{classroomEntity.className}</dd>
          <dt>Students</dt>
          <dd>
            {classroomEntity.students
              ? classroomEntity.students.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {classroomEntity.students && i === classroomEntity.students.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/classroom" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/classroom/${classroomEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ClassroomDetail;
